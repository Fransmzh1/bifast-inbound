package bifast.inbound.settlement;

import java.util.HashMap;
import java.util.List;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import bifast.inbound.config.Config;
import bifast.inbound.corebank.pojo.CbSettlementRequestPojo;
import bifast.inbound.model.CreditTransfer;
import bifast.inbound.pojo.ProcessDataPojo;
import bifast.inbound.pojo.flat.FlatPacs002Pojo;
import bifast.inbound.repository.CreditTransferRepository;
import bifast.inbound.repository.SettlementRepository;

@Component
public class BuildCreditSettlementProcessor implements Processor {
	@Autowired private Config config;
	@Autowired private CreditTransferRepository ctRepo;
	@Autowired private SettlementRepository settlementRepo;

//	private static Logger logger = LoggerFactory.getLogger(SettlementProcessor.class);

	@Override
	public void process(Exchange exchange) throws Exception {
		
		HashMap<String, Object> arr = exchange.getMessage().getHeader("ctsaf_qryresult", HashMap.class);
		
		ProcessDataPojo processData = exchange.getMessage().getHeader("hdr_process_data", ProcessDataPojo.class);
		FlatPacs002Pojo flatSttl = (FlatPacs002Pojo) processData.getBiRequestFlat();

		CbSettlementRequestPojo sttlRequest = new CbSettlementRequestPojo();
		sttlRequest.setKomiTrnsId(processData.getKomiTrnsId());
		
		sttlRequest.setBizMsgId(flatSttl.getBizMsgIdr());
		sttlRequest.setMsgId(flatSttl.getMsgId());	
		
			
		List<CreditTransfer> lCrdtTrns = ctRepo.findAllByCrdtTrnRequestBizMsgIdr(flatSttl.getOrgnlEndToEndId());
		for (CreditTransfer runningCT : lCrdtTrns) {
			if ((runningCT.getResponseCode().equals("ACTC")) ||
				(runningCT.getResponseCode().equals("ACSC"))) {

				sttlRequest.setOrgnlKomiTrnsId(runningCT.getKomiTrnsId());
				if (config.getBankcode().equals(runningCT.getOriginatingBank()))
					sttlRequest.setCounterParty(runningCT.getRecipientBank());
				else
					sttlRequest.setCounterParty(runningCT.getOriginatingBank());

				break;
			}
		}


		exchange.getMessage().setBody(sttlRequest);

		
		
				
	}

}