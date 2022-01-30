package bifast.outbound.accountenquiry.processor;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.stereotype.Component;

import bifast.outbound.accountenquiry.pojo.ChnlAccountEnquiryRequestPojo;
import bifast.outbound.pojo.FaultPojo;
import bifast.outbound.pojo.RequestMessageWrapper;
import bifast.outbound.pojo.ResponseMessageCollection;
import bifast.outbound.pojo.flat.FlatPrxy004Pojo;

@Component
public class AEProxyEnrichmentProcessor implements Processor{

	@Override
	public void process(Exchange exchange) throws Exception {
		
		ResponseMessageCollection rmc = exchange.getProperty("prop_response_list", ResponseMessageCollection.class);

		Object oResp = exchange.getMessage().getBody(Object.class);

		if (oResp.getClass().getSimpleName().equals("FaultPojo")) {
			FaultPojo fault = (FaultPojo) oResp;
			rmc.setFault(fault);
			rmc.setResponseCode(fault.getResponseCode());
			rmc.setReasonCode(fault.getReasonCode());

		}

		if (oResp.getClass().getSimpleName().equals("FlatPrxy004Pojo")) {
			FlatPrxy004Pojo prx004 = (FlatPrxy004Pojo)oResp;

			rmc.setResponseCode(prx004.getResponseCode());
			rmc.setReasonCode(prx004.getReasonCode());
			rmc.setProxyResolutionResponse(prx004);
			
			exchange.setProperty("prop_response_list", rmc);

			RequestMessageWrapper rmw = exchange.getMessage().getHeader("hdr_request_list", RequestMessageWrapper.class);
			ChnlAccountEnquiryRequestPojo aeReq = rmw.getChnlAccountEnquiryRequest();
			if (prx004.getResponseCode().equals("ACTC")) {
				aeReq.setCreditorAccountNumber(prx004.getAccountNumber());
				aeReq.setRecptBank(prx004.getRegisterBank());
			}

			rmw.setChnlAccountEnquiryRequest(aeReq);
			exchange.setProperty("prop_request_list", rmw);
			
			if (prx004.getResponseCode().equals("ACTC"))
				exchange.getMessage().setBody(aeReq);
			else
				exchange.getMessage().setBody(prx004);
				
		}
	}
}
