package bifast.outbound.accountenquiry.processor;

import java.util.List;

import org.apache.camel.Exchange;
import org.apache.camel.MessageHistory;
import org.apache.camel.Processor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import bifast.library.iso20022.custom.BusinessMessage;
import bifast.library.iso20022.pacs008.FIToFICustomerCreditTransferV08;
import bifast.outbound.model.AccountEnquiry;
import bifast.outbound.repository.AccountEnquiryRepository;
import bifast.outbound.service.UtilService;

@Component
public class SaveAccountEnquiryProcessor implements Processor {

	@Autowired
	private AccountEnquiryRepository accountEnqrRepo;
	@Autowired
	private UtilService utilService;

	@Override
	public void process(Exchange exchange) throws Exception {

		@SuppressWarnings("unchecked")
		List<MessageHistory> listHistory = exchange.getProperty(Exchange.MESSAGE_HISTORY,List.class);

		long routeElapsed = utilService.getRouteElapsed(listHistory, "komi.call-cihub");

		BusinessMessage outRequest = exchange.getMessage().getHeader("hdr_cihub_request", BusinessMessage.class);
		
		AccountEnquiry ae = new AccountEnquiry();

		String chnlRefId = exchange.getMessage().getHeader("hdr_chnlRefId", String.class);				

		ae.setIntrRefId(chnlRefId);

		Long chnlTrxId = exchange.getMessage().getHeader("hdr_chnlTable_id", Long.class);
		if (!(null == chnlTrxId))
			ae.setChnlTrxId(chnlTrxId);
		
		ae.setBizMsgIdr(outRequest.getAppHdr().getBizMsgIdr());

		String orgnlBank = outRequest.getAppHdr().getFr().getFIId().getFinInstnId().getOthr().getId();
		String recptBank = outRequest.getAppHdr().getTo().getFIId().getFinInstnId().getOthr().getId();
		ae.setOriginatingBank(orgnlBank);
		ae.setRecipientBank(recptBank);

		FIToFICustomerCreditTransferV08 accountEnqReq = outRequest.getDocument().getFiToFICstmrCdtTrf();
		
//		ae.setCreDt(accountEnqReq.getGrpHdr().getCreDtTm().toGregorianCalendar().toZonedDateTime().toLocalDateTime());

		ae.setAccountNo(accountEnqReq.getCdtTrfTxInf().get(0).getCdtrAcct().getId().getOthr().getId());
		ae.setAmount(accountEnqReq.getCdtTrfTxInf().get(0).getIntrBkSttlmAmt().getValue());

		ae.setCihubRequestDT(utilService.getTimestampFromMessageHistory(listHistory, "start_route"));
		ae.setCihubElapsedTime(routeElapsed);
		
		String encrRequestMsg = exchange.getMessage().getHeader("cihubroute_encr_request", String.class);
		String encrResponseMsg = exchange.getMessage().getHeader("cihubroute_encr_response", String.class);

		ae.setFullRequestMessage(encrRequestMsg);
		ae.setFullResponseMsg(encrResponseMsg);

		BusinessMessage outResponse = exchange.getMessage().getHeader("hdr_cihub_response", BusinessMessage.class);

		if (!(null==outResponse)) {
			ae.setRespBizMsgIdr(outResponse.getAppHdr().getBizMsgIdr());
			ae.setResponseStatus(outResponse.getDocument().getFiToFIPmtStsRpt().getTxInfAndSts().get(0).getTxSts());
		}

		String errorStatus = exchange.getMessage().getHeader("hdr_error_status", String.class);
    	String errorMesg = exchange.getMessage().getHeader("hdr_error_mesg", String.class);

		if (!(null==errorStatus)) {
			ae.setCallStatus(errorStatus);
			if (!(null==errorMesg))
				ae.setErrorMessage(errorMesg);
		}
		else
			ae.setCallStatus("SUCCESS");

		
		
		accountEnqrRepo.save(ae);
		
	} 

	
}