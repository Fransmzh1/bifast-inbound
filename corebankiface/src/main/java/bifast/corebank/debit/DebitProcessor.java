package bifast.corebank.debit;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.stereotype.Component;

@Component
public class DebitProcessor implements Processor{

	@Override
	public void process(Exchange exchange) throws Exception {

		CbDebitRequestPojo debitRequest = exchange.getMessage().getBody(CbDebitRequestPojo.class);
		
		CbDebitResponsePojo debitResponse = new CbDebitResponsePojo();
		
		debitResponse.setKomiTrnsId(debitRequest.getKomiTrnsId());

		debitResponse.setAccountNumber(debitRequest.getCreditorAccountNumber());
		debitResponse.setAdditionalInfo(debitRequest.getPaymentInformation());
		
		debitResponse.setMerchantType(debitRequest.getMerchantType());
		debitResponse.setNoRef(debitRequest.getNoRef());
		
		if (debitRequest.getCategoryPurpose().equals("02")) {
			debitResponse.setStatus("RJCT");
			debitResponse.setReason("U111");
		} 
		else {
			debitResponse.setStatus("ACTC");
			debitResponse.setReason("U000");
		}

		debitResponse.setTerminalId(debitRequest.getTerminalId());
		
		exchange.getMessage().setBody(debitResponse);
	
	}
}
