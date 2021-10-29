package bifast.outbound.credittransfer.processor;

import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import bifast.outbound.config.Config;
import bifast.outbound.corebank.pojo.CbDebitRequestPojo;
import bifast.outbound.pojo.RequestMessageWrapper;
import bifast.outbound.pojo.chnlrequest.ChnlCreditTransferRequestPojo;
import bifast.outbound.service.CorebankService;

@Component
public class BuildCBDebitRequestProcessor implements Processor{
	@Autowired
	private CorebankService cbService;
	@Autowired
	private Config config;
	
	@Override
	public void process(Exchange exchange) throws Exception {
		RequestMessageWrapper rmw = exchange.getMessage().getHeader("hdr_request_list", RequestMessageWrapper.class);
		ChnlCreditTransferRequestPojo chnReq = rmw.getChnlCreditTransferRequest();
		
		CbDebitRequestPojo debitReq = new CbDebitRequestPojo();
		
				
		debitReq.setTransactionId("000000");

		debitReq.setDateTime(cbService.getCurrentDatetime());
		debitReq.setTrnsDt(cbService.getCurrentDateOnly());
		
		debitReq.setNoRef(cbService.getKomiNoref(rmw));
		
		debitReq.setOriginalNoRef(cbService.getOriginalNoref(rmw));
		
		debitReq.setOriginalDateTime(cbService.getOriginalDatetime(rmw));

		debitReq.setMerchantType(rmw.getMerchantType());
		debitReq.setTerminalId(chnReq.getTerminalId());

		debitReq.setCategoryPurpose(chnReq.getCategoryPurpose());
		
		debitReq.setDebtorName(chnReq.getDbtrName());
		debitReq.setDebtorType(chnReq.getDbtrType());
		debitReq.setDebtorId(chnReq.getDbtrId());
		debitReq.setDebtorAccountNumber(chnReq.getDbtrAccountNo());	
		debitReq.setDebtorAccountType(chnReq.getDbtrAccountType());
		debitReq.setDebtorResidentStatus(chnReq.getDbtrResidentialStatus());
		debitReq.setDebtorTownName(chnReq.getDbtrTownName());
		
		debitReq.setAmount(chnReq.getAmount());
		debitReq.setFeeTransfer(chnReq.getFeeTransfer());

		debitReq.setRecipientBank(config.getBankcode()); 
		
		debitReq.setCreditorName(chnReq.getCrdtName());
		debitReq.setCreditorType(chnReq.getCrdtType());
		debitReq.setCreditorId(chnReq.getCrdtId());
		debitReq.setCreditorAccountNumber(chnReq.getCrdtAccountNo());
		debitReq.setCreditorAccountType(chnReq.getCrdtAccountType());
		debitReq.setCreditorResidentStatus(chnReq.getCrdtResidentialStatus());
		debitReq.setCreditorTownName(chnReq.getCrdtTownName());

		debitReq.setCreditorProxyId(chnReq.getCrdtProxyIdValue());
		debitReq.setCreditorProxyType(chnReq.getCrdtProxyIdType());

		debitReq.setPaymentInformation(chnReq.getPaymentInfo());
		
		rmw.setDebitAccountRequest(debitReq);
		
		exchange.getMessage().setHeader("hdr_request_list", rmw);
		exchange.getMessage().setBody(debitReq);
	}

}
