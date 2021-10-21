package bifast.outbound.accountenquiry.processor;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Optional;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import bifast.outbound.model.StatusReason;
import bifast.outbound.pojo.ChnlFailureResponsePojo;
import bifast.outbound.pojo.RequestMessageWrapper;
import bifast.outbound.pojo.chnlrequest.ChnlAccountEnquiryRequestPojo;
import bifast.outbound.pojo.chnlresponse.ChannelResponseWrapper;
import bifast.outbound.pojo.chnlresponse.ChnlAccountEnquiryResponsePojo;
import bifast.outbound.pojo.flat.FlatPacs002Pojo;
import bifast.outbound.repository.StatusReasonRepository;

@Component
public class AccountEnquiryResponseProcessor implements Processor {

    DateTimeFormatter dateformatter = DateTimeFormatter.ofPattern("yyyyMMdd");
    DateTimeFormatter timeformatter = DateTimeFormatter.ofPattern("HHmmss");

    @Autowired
    private StatusReasonRepository statusReasonRepo;
    
	@Override
	public void process(Exchange exchange) throws Exception {

		ChannelResponseWrapper channelResponseWr = new ChannelResponseWrapper();
		channelResponseWr.setResponseCode("U000");
		channelResponseWr.setDate(LocalDateTime.now().format(dateformatter));
		channelResponseWr.setTime(LocalDateTime.now().format(timeformatter));
		channelResponseWr.setContent(new ArrayList<>());

		RequestMessageWrapper rmw = exchange.getMessage().getHeader("hdr_request_list",RequestMessageWrapper.class);
		ChnlAccountEnquiryRequestPojo chnReq = rmw.getChnlAccountEnquiryRequest();
		
		ChnlAccountEnquiryResponsePojo chnResp = new ChnlAccountEnquiryResponsePojo();
		chnResp.setOrignReffId(chnReq.getChannelRefId());

		Object objBody = exchange.getMessage().getBody(Object.class);
		if (objBody.getClass().getSimpleName().equals("ChnlFailureResponsePojo")) {
			ChnlFailureResponsePojo fault = (ChnlFailureResponsePojo)objBody;
			
			channelResponseWr.setResponseCode("KSTS");
			channelResponseWr.setReasonCode(fault.getErrorCode());
			Optional<StatusReason> oStatusReason = statusReasonRepo.findById(fault.getErrorCode());
			if (oStatusReason.isPresent())
				channelResponseWr.setReasonMessage(oStatusReason.get().getDescription());
			else
				channelResponseWr.setResponseMessage("General Error");
		}
		
		else if (objBody.getClass().getSimpleName().equals("FlatAdmi002Pojo")) {

			channelResponseWr.setResponseCode("RJCT");
			channelResponseWr.setReasonCode("U215");
			Optional<StatusReason> oStatusReason = statusReasonRepo.findById("U215");
			if (oStatusReason.isPresent())
				channelResponseWr.setReasonMessage(oStatusReason.get().getDescription());
			else
				channelResponseWr.setResponseMessage("General Error");

		}

		else {
			
			FlatPacs002Pojo bm = (FlatPacs002Pojo)objBody;
									
			channelResponseWr.setResponseCode(bm.getTransactionStatus());
			channelResponseWr.setReasonCode(bm.getReasonCode());
			
			Optional<StatusReason> optStatusReason = statusReasonRepo.findById(channelResponseWr.getReasonCode());
			if (optStatusReason.isPresent()) {
				String desc = optStatusReason.get().getDescription();
				channelResponseWr.setReasonMessage(desc);
			}	
			
			chnResp.setCreditorAccountNumber(bm.getCdtrAcctId());
			chnResp.setCreditorAccountType(bm.getCdtrAcctTp());
			
			if (null != chnReq.getProxyId())
				chnResp.setProxyId(chnReq.getProxyId());
			
			if (null != chnReq.getProxyType())
				chnResp.setProxyType(chnReq.getProxyType());

			if (null != bm.getCdtrNm())
				chnResp.setCreditorName(bm.getCdtrNm());
			
			if (null != bm.getCdtrId())
				chnResp.setCreditorId(bm.getCdtrId());

			if (null != bm.getCdtrTp())
				chnResp.setCreditorType(bm.getCdtrTp());
			
			if (null != bm.getCdtrRsdntSts())
				chnResp.setCreditorResidentStatus(bm.getCdtrRsdntSts());
			
			if (null != bm.getCdtrTwnNm())
				chnResp.setCreditorTownName(bm.getCdtrTwnNm());
		}

		channelResponseWr.getContent().add(chnResp);

		exchange.getMessage().setBody(channelResponseWr);
	}

}
