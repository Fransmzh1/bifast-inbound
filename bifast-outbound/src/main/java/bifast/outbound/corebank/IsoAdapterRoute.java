package bifast.outbound.corebank;

import org.apache.camel.Exchange;
import org.apache.camel.LoggingLevel;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.jackson.JacksonDataFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

import bifast.outbound.corebank.pojo.AccountCustInfoRequestDTO;
import bifast.outbound.corebank.pojo.AccountCustInfoResponseDTO;
import bifast.outbound.corebank.pojo.DebitRequestDTO;
import bifast.outbound.corebank.pojo.DebitResponseDTO;
import bifast.outbound.corebank.pojo.DebitReversalRequestPojo;
import bifast.outbound.corebank.pojo.DebitReversalResponsePojo;
import bifast.outbound.corebank.processor.CbCallFaultProcessor;
import bifast.outbound.pojo.FaultPojo;
import bifast.outbound.processor.EnrichmentAggregator;
import bifast.outbound.service.JacksonDataFormatService;



@Component
public class IsoAdapterRoute extends RouteBuilder{
	@Autowired private JacksonDataFormatService jdfService;
	@Autowired private EnrichmentAggregator enrichmentAggregator;
	@Autowired private CbCallFaultProcessor cbFaultProcessor;
	@Autowired private SaveCBTableProcessor saveCBTransactionProc;

	@Override
	public void configure() throws Exception {
		JacksonDataFormat aciRequestJDF = jdfService.basic(AccountCustInfoRequestDTO.class);
		JacksonDataFormat aciResponseJDF = jdfService.basic(AccountCustInfoResponseDTO.class);
		JacksonDataFormat debitRequestJDF = jdfService.basic(DebitRequestDTO.class);
		JacksonDataFormat debitResponseJDF = jdfService.basic(DebitResponseDTO.class);
		JacksonDataFormat debitReversalReqJDF = jdfService.basic(DebitReversalRequestPojo.class);
		JacksonDataFormat debitReversalResponseJDF = jdfService.basic(DebitReversalResponsePojo.class);

		// ROUTE CALLCB 
		from("direct:isoadpt").routeId("komi.isoadapter")
//			.setProperty("bkp_hdr_process_data").header("hdr_process_data")

			.removeHeaders("*")

			.setHeader("cb_msgname", simple("${exchangeProperty[prop_process_data.inbMsgName]}"))
			.setHeader("cb_e2eid", simple("${exchangeProperty[prop_process_data.endToEndId]}"))

			.setProperty("pr_cbrequest", simple("${body}"))
			
			.log(LoggingLevel.DEBUG,"komi.isoadapter", "[${exchangeProperty.prop_request_list.msgName}:"
					+ "${exchangeProperty.prop_request_list.requestId}] Terima di corebank: ${body}")
					
			.choice()
				.when().simple("${body.class} endsWith 'AccountCustInfoRequestDTO'")
					.setProperty("pr_cbRequestName", constant("accountcustinfo"))
					.setHeader("cb_url", simple("{{komi.url.isoadapter.customerinfo}}"))
					.marshal(aciRequestJDF)
				.when().simple("${body.class} endsWith 'DebitRequestDTO'")
					.setProperty("pr_cbRequestName", constant("debit"))
					.setHeader("cb_url", simple("{{komi.url.isoadapter.debit}}"))
					.marshal(debitRequestJDF)
				.when().simple("${body.class} endsWith 'DebitReversalRequestPojo'")
					.setProperty("pr_cbRequestName", constant("debitreversal"))
					.marshal(debitReversalReqJDF)
					.setHeader("cb_url", simple("{{komi.url.isoadapter.reversal}}"))

				.otherwise()
		 			.log("Akan kirim : ${body.class}")
			.end()
			
			.setProperty("cb_request_str", simple("${body}"))
			
	 		.log(LoggingLevel.DEBUG, "komi.isoadapter", "[${exchangeProperty.prop_request_list.requestId}:"
	 				+ "${exchangeProperty.prop_request_list.requestId}] POST ${header.cb_url}")
	 		.log("[${exchangeProperty.prop_request_list.requestId}:${exchangeProperty.prop_request_list.requestId}] CB Request: ${body}")
			
	 		.doTry()
				.setHeader("HttpMethod", constant("POST"))
				.setHeader(Exchange.CONTENT_TYPE, constant(MediaType.APPLICATION_JSON))
				.enrich().simple("${header.cb_url}?bridgeEndpoint=true")
					
				.aggregationStrategy(enrichmentAggregator)
				.convertBodyTo(String.class)
				
				.log("[${exchangeProperty.prop_request_list.msgName}:${header.cb_e2eid}] CB response: ${body}")

		    	.choice()
		 			.when().simple("${exchangeProperty.pr_cbRequestName} == 'accountcustinfo'")
		 				.unmarshal(aciResponseJDF)
	 				.endChoice()
		 			.when().simple("${exchangeProperty.pr_cbRequestName} == 'debit'")
		 				.unmarshal(debitResponseJDF)
		 			.when().simple("${exchangeProperty.pr_cbRequestName} == 'debitreversal'")
	 					.unmarshal(debitReversalResponseJDF)
	 				.endChoice()
		 		.end()

 				.setProperty("pr_response", simple("${body.status}"))
 				.setProperty("pr_reason", simple("${body.reason}"))

				.filter().simple("${exchangeProperty.pr_response} != 'ACTC' ")
					.process(new Processor() {
						public void process(Exchange exchange) throws Exception {
							FaultPojo fault = new FaultPojo();
							String cbResponse = exchange.getProperty("pr_response", String.class);
							String cbReason = exchange.getProperty("pr_reason", String.class);
							fault.setResponseCode(cbResponse);
							fault.setReasonCode(cbReason);
							exchange.getMessage().setBody(fault);
							}
						})
				.end()
				
	 		.endDoTry()
	    	.doCatch(Exception.class)
				.log(LoggingLevel.ERROR, "[${exchangeProperty.prop_request_list.msgName}:${header.cb_e2eid}] Call CB Error.")
		    	.log(LoggingLevel.ERROR, "${exception.stacktrace}")
		    	.process(cbFaultProcessor)
	    	.end()

			.log("[${exchangeProperty.prop_request_list.msgName}:${header.cb_e2eid}] CB response: ${body}")

			.filter().simple("${header.cb_requestName} in 'debit,debitreversal'")
				.log("akan simpan ${header.cb_requestName}")
				.process(saveCBTransactionProc)
			.end()
			
			.removeHeaders("cb_*")
		;
		
		
	}

}
