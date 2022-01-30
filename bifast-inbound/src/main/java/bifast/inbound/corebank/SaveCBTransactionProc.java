package bifast.inbound.corebank;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import bifast.inbound.corebank.isopojo.CreditRequest;
import bifast.inbound.corebank.isopojo.SettlementRequest;
import bifast.inbound.model.CorebankTransaction;
import bifast.inbound.repository.CorebankTransactionRepository;

@Component
public class SaveCBTransactionProc implements Processor {
	@Autowired private CorebankTransactionRepository cbRepo;
	
	@Override
	public void process(Exchange exchange) throws Exception {

		String strRequest = exchange.getProperty("cb_request_str", String.class);
		Object oCbRequest = exchange.getProperty("cb_request", Object.class);
		String response = exchange.getMessage().getHeader("cb_response", String.class);
		String reason = exchange.getMessage().getHeader("cb_reason", String.class);
		
		CorebankTransaction corebankTrans = new CorebankTransaction();

		corebankTrans.setUpdateTime(LocalDateTime.now());
		corebankTrans.setFullTextRequest(strRequest);
		corebankTrans.setTrnsDate(LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMMdd")));

		corebankTrans.setReason(reason);
		corebankTrans.setResponse(response);

		if (oCbRequest.getClass().getSimpleName().equals("CreditRequest")) {
			CreditRequest req = (CreditRequest) oCbRequest;
			
			corebankTrans.setCreditAmount(new BigDecimal(req.getAmount()));
			corebankTrans.setCstmAccountName(req.getCreditorName());
			corebankTrans.setCstmAccountNo(req.getCreditorAccountNumber());
			corebankTrans.setCstmAccountType(req.getCreditorAccountType());
			corebankTrans.setDateTime(req.getDateTime());
//			corebankTrans.setDebitAmount(null);
			corebankTrans.setFeeAmount(new BigDecimal(req.getFeeTransfer()));
			
			corebankTrans.setKomiNoref(req.getNoRef());
			corebankTrans.setKomiTrnsId(req.getTransactionId());
			corebankTrans.setOrgnlChnlNoref(req.getOriginalNoRef());
			corebankTrans.setOrgnlDateTime(req.getOriginalDateTime());
						
			corebankTrans.setTransactionType("Credit");
		}
		
		else if (oCbRequest.getClass().getSimpleName().equals("Settlement")) {
			SettlementRequest req = (SettlementRequest) oCbRequest;
			
			corebankTrans.setDateTime(req.getDateTime());
			corebankTrans.setKomiNoref(req.getNoRef());
			corebankTrans.setKomiTrnsId(req.getTransactionId());
			corebankTrans.setOrgnlChnlNoref(req.getOriginalNoRef());
						
			corebankTrans.setTransactionType("Settlement");
		}
		
		cbRepo.save(corebankTrans);
	}

}
