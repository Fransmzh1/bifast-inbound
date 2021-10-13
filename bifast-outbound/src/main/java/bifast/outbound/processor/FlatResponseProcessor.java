package bifast.outbound.processor;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import bifast.library.iso20022.custom.BusinessMessage;
import bifast.outbound.pojo.ChnlFailureResponsePojo;
import bifast.outbound.pojo.flat.FlatAdmi002Pojo;
import bifast.outbound.pojo.flat.FlatMessageWrapper;
import bifast.outbound.pojo.flat.FlatPacs002Pojo;
import bifast.outbound.pojo.flat.FlatPrxy004Pojo;
import bifast.outbound.pojo.flat.FlatPrxy006Pojo;
import bifast.outbound.service.FlattenIsoMessageService;
import bifast.outbound.service.UtilService;

@Component
public class FlatResponseProcessor implements Processor {

	@Autowired
	private FlattenIsoMessageService flattenMessageService;
	@Autowired
	private UtilService utilService;
	
	@Override
	public void process(Exchange exchange) throws Exception {
		
		FlatMessageWrapper messageWr = new FlatMessageWrapper();
		
		BusinessMessage busMesg = exchange.getIn().getBody(BusinessMessage.class);

		if (null == busMesg) {
			
			ChnlFailureResponsePojo reject = new ChnlFailureResponsePojo();
			
			String errorStatus = exchange.getMessage().getHeader("hdr_error_status", String.class);
			String errorMesg = exchange.getMessage().getHeader("hdr_error_mesg", String.class);
			reject.setReason(errorStatus);
			reject.setDescription(errorMesg);
			
			messageWr.setFaultResponse(reject);

		}
		
		else {
			
			String msgType = utilService.getMsgType(busMesg.getAppHdr().getMsgDefIdr(), busMesg.getAppHdr().getBizMsgIdr());

			if (msgType.equals("AccountEnquiryResponse")) {
			
				FlatPacs002Pojo flatPacs002 = flattenMessageService.flatteningPacs002(busMesg);
				messageWr.setAccountEnquiryResponse(flatPacs002);	
			}

			else if (msgType.equals("CreditTransferResponse")) {
				
				FlatPacs002Pojo flatPacs002 = flattenMessageService.flatteningPacs002(busMesg);
				messageWr.setCreditTransferResponse(flatPacs002);	
			}

			else if (msgType.equals("FICreditTransferResponse")) {
				
				FlatPacs002Pojo flatPacs002 = flattenMessageService.flatteningPacs002(busMesg);
				messageWr.setFiCreditTransferResponse(flatPacs002);	
			}

		}
		
		
//		else if (busMesg.getAppHdr().getMsgDefIdr().equals("pacs.002.001.10")) {
//						
//			FlatPacs002Pojo flatPacs002 = flattenMessageService.flatteningPacs002(busMesg);
//			messageWr.setFlatPacs002(flatPacs002);	
//		}
//		
//		else if (busMesg.getAppHdr().getMsgDefIdr().equals("admi.002.001.01")) {
//			
//			FlatAdmi002Pojo flatAdmi002 = flattenMessageService.flatteningAdmi002(busMesg);
//			messageWr.setFlatAdmi002(flatAdmi002);
//		}
//		
//		else if (busMesg.getAppHdr().getMsgDefIdr().equals("prxy.004.001.01")) {
//			
//			FlatPrxy004Pojo flatPrxy004 = flattenMessageService.flatteningPrxy004(busMesg);
//			messageWr.setFlatPrxy004(flatPrxy004);
//		}
//		
//		else if (busMesg.getAppHdr().getMsgDefIdr().equals("prxy.006.001.01")) {
//			
//			FlatPrxy006Pojo flatPrxy006 = flattenMessageService.flatteningPrxy006(busMesg);
//			messageWr.setFlatPrxy006(flatPrxy006);
//		}
		
		exchange.getMessage().setBody(messageWr);
	}

}
