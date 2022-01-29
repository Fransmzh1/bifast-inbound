package bifast.inbound.credittransfer.processor;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import bifast.inbound.model.CreditTransfer;
import bifast.inbound.pojo.ProcessDataPojo;
import bifast.inbound.pojo.flat.FlatPacs008Pojo;
import bifast.inbound.repository.CreditTransferRepository;
import bifast.library.iso20022.custom.BusinessMessage;

@Component
public class SaveCreditTransfer2Processor implements Processor {

	@Autowired private CreditTransferRepository creditTrnRepo;

	@Override
	public void process(Exchange exchange) throws Exception {
		 

//		BusinessMessage rcvBi = exchange.getMessage().getHeader("hdr_frBIobj",BusinessMessage.class);
//		BusinessApplicationHeaderV01 hdr = rcvBi.getAppHdr();

		ProcessDataPojo processData = exchange.getMessage().getHeader("hdr_process_data", ProcessDataPojo.class);
		FlatPacs008Pojo flatReq = (FlatPacs008Pojo)processData.getBiRequestFlat();
		
		CreditTransfer ct = new CreditTransfer();

		ct.setKomiTrnsId(processData.getKomiTrnsId());
		
//		String fullReqMsg = exchange.getMessage().getHeader("hdr_frBI_jsonzip",String.class);
		String fullReqMsg = exchange.getProperty("prop_frBI_jsonzip",String.class);
		String fullRespMsg = exchange.getMessage().getHeader("hdr_toBI_jsonzip",String.class);
		
		System.out.println(fullReqMsg);
		ct.setFullRequestMessage(fullReqMsg);
		ct.setFullResponseMsg(fullRespMsg);
		
		if (null != processData.getBiResponseMsg()) {
			BusinessMessage respBi = processData.getBiResponseMsg();

			ct.setResponseCode(respBi.getDocument().getFiToFIPmtStsRpt().getTxInfAndSts().get(0).getTxSts());
			ct.setCrdtTrnResponseBizMsgIdr(respBi.getAppHdr().getBizMsgIdr());
			ct.setReasonCode(respBi.getDocument().getFiToFIPmtStsRpt().getTxInfAndSts().get(0).getStsRsnInf().get(0).getRsn().getPrtry());
			ct.setCallStatus("SUCCESS");
			ct.setCbStatus("PENDING");
		}
		
		ct.setCihubRequestDT(processData.getReceivedDt());
		long timeElapsed = Duration.between(processData.getStartTime(), Instant.now()).toMillis();
		ct.setCihubElapsedTime(timeElapsed);

		ct.setCreateDt(LocalDateTime.now());
		ct.setLastUpdateDt(LocalDateTime.now());

//		FIToFICustomerCreditTransferV08 creditTransferReq = rcvBi.getDocument().getFiToFICstmrCdtTrf();
		
		ct.setAmount(flatReq.getAmount());
		ct.setCrdtTrnRequestBizMsgIdr(flatReq.getBizMsgIdr());
		ct.setEndToEndId(flatReq.getEndToEndId());
		ct.setCreditorAccountNumber(flatReq.getCreditorAccountNo());

		if (!(null == flatReq.getCreditorAccountType()))
			ct.setCreditorAccountType(flatReq.getCreditorAccountType());
		
		if (null!=flatReq.getCreditorType()) 
			ct.setCreditorType(flatReq.getCreditorType());

		if (!(null==flatReq.getCreditorId()))
			ct.setCreditorId(flatReq.getCreditorId());
				
		
		ct.setDebtorAccountNumber(flatReq.getDebtorAccountNo());
		ct.setDebtorAccountType(flatReq.getDebtorAccountType());
		
		if (null != flatReq.getDebtorType())
				ct.setDebtorType(flatReq.getDebtorType());

		if (!(null==flatReq.getDebtorId()))
			ct.setDebtorId(flatReq.getDebtorId());

		if (processData.getInbMsgName().equals("CrdTrn"))
			ct.setMsgType("Credit Transfer");
		else
			ct.setMsgType("Reverse CT");
				
		ct.setOriginatingBank(flatReq.getDebtorAgentId());
		ct.setRecipientBank(flatReq.getCreditorAgentId());

		ct.setSettlementConfBizMsgIdr("WAITING");
		
		String reversal = exchange.getMessage().getHeader("hdr_reversal",String.class);
		ct.setReversal(reversal);
		
		if (null != flatReq.getCpyDplct())
			ct.setCpyDplct(flatReq.getCpyDplct());
		
		creditTrnRepo.save(ct);
	}
	
}