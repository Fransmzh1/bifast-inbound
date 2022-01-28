package bifast.inbound.settlement;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import bifast.inbound.config.Config;
import bifast.inbound.model.ChannelTransaction;
import bifast.inbound.model.CreditTransfer;
import bifast.inbound.model.Settlement;
import bifast.inbound.pojo.ProcessDataPojo;
import bifast.inbound.pojo.flat.FlatPacs002Pojo;
import bifast.inbound.repository.ChannelTransactionRepository;
import bifast.inbound.repository.CreditTransferRepository;
import bifast.inbound.repository.SettlementRepository;

@Component
public class SaveSettlementMessageProcessor implements Processor {
	@Autowired private ChannelTransactionRepository chnlRepo;
	@Autowired private Config config;
	@Autowired private CreditTransferRepository ctRepo;
	@Autowired private SettlementRepository settlementRepo;

	private static Logger logger = LoggerFactory.getLogger(SaveSettlementMessageProcessor.class);

	public void process(Exchange exchange) throws Exception {
		 
		ProcessDataPojo processData = exchange.getMessage().getHeader("hdr_process_data", ProcessDataPojo.class);
		FlatPacs002Pojo flatSttl = (FlatPacs002Pojo) processData.getBiRequestFlat();

		String fullReqMsg = exchange.getMessage().getHeader("hdr_frBI_jsonzip",String.class);
		
		Settlement sttl = new Settlement();
//		sttl.setOrgnlCTBizMsgId(flatSttl.getOrgnlEndToEndId());
		sttl.setSettlBizMsgId(flatSttl.getBizMsgIdr());
		sttl.setOrgnlEndToEndId(flatSttl.getOrgnlEndToEndId());
		
		sttl.setDbtrBank(flatSttl.getDbtrAgtFinInstnId());
		sttl.setCrdtBank(flatSttl.getCdtrAgtFinInstnId());
		
		if (!(null == flatSttl.getCdtrAcctId()))
			sttl.setCrdtAccountNo(flatSttl.getCdtrAcctId());
		
		
		if (!(null == flatSttl.getDbtrAcctId()))
			sttl.setDbtrAccountNo(flatSttl.getDbtrAcctId());

		sttl.setReceiveDate(LocalDateTime.now());
		sttl.setFullMessage(fullReqMsg);
		
		settlementRepo.save(sttl);
		
///////////////////////		
		
		List<CreditTransfer> lCrdtTrns = ctRepo.findAllByCrdtTrnRequestBizMsgIdr(flatSttl.getOrgnlEndToEndId());
		CreditTransfer ct = null;
		for (CreditTransfer runningCT : lCrdtTrns) {
			if ((runningCT.getResponseCode().equals("ACTC")) ||
				(runningCT.getResponseCode().equals("ACSC"))) {
				ct = runningCT;
				break;
			}
		}

		String settlment_ctType = "Outbound";
//		logger.debug(sttl.getCrdtBank() + " vs " + config.getBankcode());
		
		if (null != ct) {
			ct.setSettlementConfBizMsgIdr(flatSttl.getBizMsgIdr());
			ct.setLastUpdateDt(LocalDateTime.now());
			
			if (sttl.getCrdtBank().equals(config.getBankcode())) {
				ct.setCbStatus("READY");
				settlment_ctType = "Inbound";
			}
			ctRepo.save(ct);
		}
		exchange.getMessage().setHeader("sttl_transfertype", settlment_ctType);

		if (settlment_ctType.equals("Inbound")) {
			Optional<ChannelTransaction> oChnlTrns = chnlRepo.findByKomiTrnsId(ct.getKomiTrnsId());
			if (oChnlTrns.isPresent())
				exchange.getMessage().setHeader("sttl_orgnRef", oChnlTrns.get().getChannelRefId());
			else
				exchange.getMessage().setHeader("sttl_orgnRef", flatSttl.getOrgnlEndToEndId());
				
		}
		

	}
}
