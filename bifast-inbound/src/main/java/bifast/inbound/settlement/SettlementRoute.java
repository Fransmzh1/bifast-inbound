package bifast.inbound.settlement;

import java.util.Optional;

import org.apache.camel.Exchange;
import org.apache.camel.LoggingLevel;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import bifast.inbound.credittransfer.processor.JobWakeupProcessor;
import bifast.inbound.model.CreditTransfer;
import bifast.inbound.repository.CreditTransferRepository;

@Component
public class SettlementRoute extends RouteBuilder {
	@Autowired private CreditTransferRepository ctRepo;
	@Autowired private JobWakeupProcessor jobWakeupProcessor;
	@Autowired private SaveSettlementMessageProcessor saveSettlement;
	@Autowired private SettlementDebitProcessor settlementDebitProcessor;
//	@Autowired private SettlementCreditProcessor settlementCreditProcessor;

	@Override
	public void configure() throws Exception {

		from("direct:settlement").routeId("komi.settlement")
			
			// prepare untuk request ke corebank

	 		.log(LoggingLevel.DEBUG, "komi.settlement", 
	 				"[${exchangeProperty.prop_process_data.inbMsgName}:${exchangeProperty.prop_process_data.endToEndId}] Terima settlement")

	 		.process(new Processor () {
				public void process(Exchange exchange) throws Exception {
					String e2eid = exchange.getProperty("end2endid", String.class);
					Optional<CreditTransfer> oOrgnlCT = ctRepo.getSuccessByEndToEndId(e2eid);
					if (oOrgnlCT.isPresent()) {
						CreditTransfer orgnlCT = oOrgnlCT.get();
						exchange.setProperty("pr_orgnlCT", orgnlCT);
					}}
	 		})

	 		.process(saveSettlement)
			
			.log(LoggingLevel.DEBUG, "komi.settlement", "[${exchangeProperty.prop_process_data.inbMsgName}:"
					+ "${exchangeProperty.prop_process_data.endToEndId}] Settlement for ${header.sttl_transfertype} message")
			
			.choice()
				.when().simple("${header.sttl_transfertype} == 'Inbound'")
		 			.log(LoggingLevel.DEBUG, "komi.settlement", 
		 					"[${exchangeProperty.prop_process_data.inbMsgName}:${exchangeProperty.prop_process_data.endToEndId}] activate credit-posting job")

					.process(jobWakeupProcessor)
				.otherwise()
					.process(settlementDebitProcessor)
//					.to("direct:post_credit_cb")
					.to("direct:isoadpt")
			 		.log("[${exchangeProperty.prop_process_data.inbMsgName}:${exchangeProperty.prop_process_data.endToEndId}] Selesai posting settlement")
			.end()

		;

	}

}
