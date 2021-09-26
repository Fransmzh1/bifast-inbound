package bifast.inbound.corebank;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.stereotype.Component;

import bifast.inbound.corebank.pojo.CBAccountEnquiryRequestPojo;
import bifast.inbound.corebank.pojo.CBAccountEnquiryResponsePojo;


@Component
public class BuildCBResponseProcessor implements Processor {

	@Override
	public void process(Exchange exchange) throws Exception {

		CBAccountEnquiryResponsePojo response = new CBAccountEnquiryResponsePojo();
		
		CBAccountEnquiryRequestPojo request = exchange.getMessage().getBody(CBAccountEnquiryRequestPojo.class);
		
		if (request.getAccountNumber().startsWith("11")) {
			response.setAccountNumber(request.getAccountNumber());
			response.setAccountType("CACC");
			response.setAdditionInfo("Rekening bermasalah");
			response.setCreditorId("22222");
			response.setCreditorName("Johansyah");
			response.setCreditorStatus("NOTVALID");
			response.setCreditorType("01");
	//		response.setErrorMessage(null);
//			response.setRequestStatus("SUCCESS");
			response.setResidentStatus("01");
			response.setTownName("Jakarta");
			response.setTransactionId(request.getTransactionId());
			
		}
		
		else {
			response.setAccountNumber(request.getAccountNumber());
			response.setAccountType("CACC");
//			response.setAdditionInfo("Rekening bermasalah");
			response.setCreditorId("22222");
			response.setCreditorName("Antonio");
			response.setCreditorStatus("VALID");
			response.setCreditorType("01");
	//		response.setErrorMessage(null);
//			response.setRequestStatus("SUCCESS");
			response.setResidentStatus("01");
			response.setTownName("Jakarta");
			response.setTransactionId(request.getTransactionId());
		}
		
		exchange.getMessage().setBody(response);
		
	}


}
