package bifast.outbound.credittransfer;

import java.time.format.DateTimeFormatter;

import org.apache.camel.LoggingLevel;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import bifast.outbound.credittransfer.processor.AftDebitCallProc;
import bifast.outbound.credittransfer.processor.BuildCTRequestProcessor;
import bifast.outbound.credittransfer.processor.BuildDebitRequestProcessor;
import bifast.outbound.credittransfer.processor.CreditTransferResponseProcessor;
import bifast.outbound.credittransfer.processor.StoreCreditTransferProcessor;

@Component
public class CreditTransferRoute extends RouteBuilder {

	@Autowired private BuildDebitRequestProcessor buildDebitRequestProcessor;
	@Autowired private BuildCTRequestProcessor crdtTransferProcessor;
	@Autowired private CreditTransferResponseProcessor crdtTransferResponseProcessor;
	@Autowired private StoreCreditTransferProcessor saveCrdtTrnsProcessor;
	@Autowired private AftDebitCallProc afterDebitCallProc;

	DateTimeFormatter dateformatter = DateTimeFormatter.ofPattern("yyyyMMdd");
    DateTimeFormatter timeformatter = DateTimeFormatter.ofPattern("HHmmss");
    
	@Override
	public void configure() throws Exception {
	
		from("direct:credittrns").routeId("komi.ct")
			
			.setHeader("ct_progress", constant("Start"))

			// FILTER-A debit-account di cbs dulu donk untuk e-Channel
			.setProperty("pr_response", constant("ACTC"))
			.filter().simple("${exchangeProperty.prop_request_list.merchantType} != '6010' ")
				.log(LoggingLevel.DEBUG, "komi.ct", 
						"[${exchangeProperty.prop_request_list.msgName}:${exchangeProperty.prop_request_list.requestId}] Transaksi non-teller")
				.setHeader("ct_progress", constant("CB"))
				.process(buildDebitRequestProcessor)
				.to("direct:debit")
				.process(afterDebitCallProc)

			.end()
		

	    	.filter(exchangeProperty("pr_response").isEqualTo("ACTC"))

			.log(LoggingLevel.DEBUG, "komi.ct", 
					"[${exchangeProperty.prop_request_list.msgName}:${exchangeProperty.prop_request_list.requestId}] "
					+ "setelah corebank, ${exchangeProperty.pr_response}.")

			// lanjut submit ke BI
			.process(crdtTransferProcessor)
			
			// save data awal
			.to("seda:savecredittransfer?exchangePattern=InOnly")   // data awal

			.to("direct:call-cihub?timeout=0")
					
			.log(LoggingLevel.DEBUG, "komi.ct", 
					"[${exchangeProperty.prop_request_list.msgName}:${exchangeProperty.prop_request_list.requestId}] response class dari cihub: ${body}")
			.to("seda:savecredittransfer?exchangePattern=InOnly")   // update data


			// periksa hasil call cihub
			.choice()
				.when().simple("${body.class} endsWith 'FaultPojo' && ${body.reasonCode} == 'K000'")
					.setHeader("ct_progress", constant("TIMEOUT"))
				.when().simple("${body.class} endsWith 'FaultPojo' && ${body.reasonCode} != 'K000'")
					.setHeader("ct_progress", constant("ERROR"))
				.when().simple("${body.class} endsWith 'FlatPacs002Pojo' && ${body.reasonCode} == 'U900'")
					.setHeader("ct_progress", constant("TIMEOUT"))
				.when().simple("${body.class} endsWith 'FlatPacs002Pojo' && ${body.transactionStatus} == 'ACTC'")
					.setHeader("ct_progress", constant("SUCCESS"))
				.otherwise()
					.setHeader("ct_progress", constant("REJECT"))
			.end()
			
			.log(LoggingLevel.DEBUG, "komi.ct", 
					"[${exchangeProperty.prop_request_list.msgName}:${exchangeProperty.prop_request_list.requestId}] hasil cihub, ${header.ct_progress}.")

			// jika response RJCT, harus reversal ke corebanking
			// unt Teller tidak dilakukan KOMI tapi oleh Teller langsung
			.choice()
				.when().simple("${header.ct_progress} in 'REJECT' "
					+ "&& ${exchangeProperty.prop_request_list.merchantType} != '6010'")
					.log(LoggingLevel.DEBUG, "komi.ct", 
							"[${exchangeProperty.prop_request_list.msgName}:${exchangeProperty.prop_request_list.requestId}] akan reversal")
					.to("direct:debitreversal")

				.when().simple("${header.ct_progress} == 'TIMEOUT'")
					.log(LoggingLevel.DEBUG, "komi.ct", 
						"[${exchangeProperty.prop_request_list.msgName}:${exchangeProperty.prop_request_list.requestId}] akan payment status")
//					.to("controlbus:route?routeId=komi.ps.saf&action=resume&async=true")
					.to("seda:psr?exchangePattern=InOnly")
			.end()
			
			.process(crdtTransferResponseProcessor)		
			
//			.to("seda:savechanneltrns?exchangePattern=InOnly")   // update data

			.removeHeaders("ct_*")
		;
		
		
		from("seda:savecredittransfer?concurrentConsumers=3").routeId("komi.ct.savedb")
			.log(LoggingLevel.DEBUG, "komi.ct.savedb", 
				"[${exchangeProperty.prop_request_list.msgName}:${exchangeProperty.prop_request_list.requestId}] akan save transaksi CT")
			.process(saveCrdtTrnsProcessor)
		;
		
//		from("seda:savechanneltrns?concurrentConsumers=2&blockWhenFull=true")
//			.log(LoggingLevel.DEBUG, "komi.ct.savechnl", 
//				"[${exchangeProperty.prop_request_list.msgName}:${exchangeProperty.prop_request_list.requestId}] akan save channel log")
//			.process(saveCTChannelRequestProc)
//			;
		
	}
}
