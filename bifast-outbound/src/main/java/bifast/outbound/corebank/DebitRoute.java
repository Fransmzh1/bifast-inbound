package bifast.outbound.corebank;


import org.apache.camel.Exchange;
import org.apache.camel.LoggingLevel;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.jackson.JacksonDataFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

import bifast.outbound.corebank.pojo.DebitRequestDTO;
import bifast.outbound.corebank.pojo.DebitResponseDTO;
import bifast.outbound.corebank.processor.CbCallFaultProcessor;
import bifast.outbound.corebank.processor.SaveCBTableProcessor;
import bifast.outbound.model.StatusReason;
import bifast.outbound.pojo.FaultPojo;
import bifast.outbound.pojo.ResponseMessageCollection;
import bifast.outbound.processor.EnrichmentAggregator;
import bifast.outbound.repository.StatusReasonRepository;
import bifast.outbound.service.JacksonDataFormatService;



@Component
public class DebitRoute extends RouteBuilder{
	@Autowired private CbCallFaultProcessor cbFaultProcessor;
	@Autowired private EnrichmentAggregator enrichmentAggregator;
	@Autowired private JacksonDataFormatService jdfService;
	@Autowired private SaveCBTableProcessor saveCBTransactionProc;
	@Autowired private StatusReasonRepository reasonRepo;
	
	@Override
	public void configure() throws Exception {
		JacksonDataFormat debitRequestJDF = jdfService.basic(DebitRequestDTO.class);
		JacksonDataFormat debitResponseJDF = jdfService.basic(DebitResponseDTO.class);

//		onException(Exception.class)
//			.handled(true)			
////			.maximumRedeliveries(3).redeliveryDelay(500)
//			.choice()
//				.when().simple("${exchangeProperty.CamelExceptionCaught.statusCode} == '504'")
//					.log(LoggingLevel.ERROR, "[${exchangeProperty.prop_request_list.msgName}:${exchangeProperty.prop_request_list.requestId}] ${exchangeProperty.CamelExceptionCaught}")
//				.otherwise()
//					.log(LoggingLevel.ERROR, "[${exchangeProperty.prop_request_list.msgName}:${exchangeProperty.prop_request_list.requestId}] Call CB Error.")
//					.log(LoggingLevel.ERROR, "${exception.stacktrace}")
//			.end()
//	    	.process(cbFaultProcessor)
////			.log(LoggingLevel.DEBUG, "komi.isoadapter", "[${exchangeProperty.prop_request_list.msgName}: "
////					+ "akan simpan ${exchangeProperty.prop_request_list.msgName}")
//////			.process(saveCBTransactionProc)
//		;

		// ROUTE CALLCB 
		from("direct:debit").routeId("komi.debit")
//			.errorHandler(defaultErrorHandler().maximumRedeliveries(3).redeliveryDelay(2000))
//			.setProperty("bkp_hdr_process_data").header("hdr_process_data")

			.removeHeaders("*")

//			.setHeader("cb_msgname", simple("${exchangeProperty[prop_process_data.inbMsgName]}"))
//			.setHeader("cb_e2eid", simple("${exchangeProperty[prop_process_data.endToEndId]}"))


			.setProperty("pr_cbrequest", simple("${body}"))
			
			.log(LoggingLevel.DEBUG,"komi.debit", "[${exchangeProperty.prop_request_list.msgName}:"
					+ "${exchangeProperty.prop_request_list.requestId}] Terima di corebank: ${body}")
					
			.marshal(debitRequestJDF)
			
//			.setProperty("cb_request_str", simple("${body}"))
			
	 		.log(LoggingLevel.DEBUG, "komi.debit", "[${exchangeProperty.prop_request_list.msgName}:"
	 				+ "${exchangeProperty.prop_request_list.requestId}] POST {{komi.url.isoadapter.debit}}")
	 		.log("[${exchangeProperty.prop_request_list.msgName}:${exchangeProperty.prop_request_list.requestId}] CB Request: ${body}")
			 		
	 		.doTry()
				.setHeader("HttpMethod", constant("POST"))
				.setHeader(Exchange.CONTENT_TYPE, constant(MediaType.APPLICATION_JSON))
				.enrich().simple("{{komi.url.isoadapter.debit}}?bridgeEndpoint=true")
					
				.aggregationStrategy(enrichmentAggregator)
				.convertBodyTo(String.class)
				
				.log("[${exchangeProperty.prop_request_list.msgName}:"
						+ "${exchangeProperty.prop_request_list.requestId}] CB response: ${body}")

 				.unmarshal(debitResponseJDF)

 				.setProperty("pr_response", simple("${body.status}"))
 				.setProperty("pr_reason", simple("${body.reason}"))
 			
				.filter().simple("${exchangeProperty.pr_response} != 'ACTC' ")
					.process(new Processor() {
						public void process(Exchange exchange) throws Exception {
							String cbResponse = exchange.getProperty("pr_response", String.class);
							String cbReason = exchange.getProperty("pr_reason", String.class);
							FaultPojo fault = new FaultPojo("CB-RJCT", cbResponse, cbReason);	
							StatusReason sr = reasonRepo.findById(cbReason).orElse(new StatusReason());
							fault.setErrorMessage(cbReason + ":" + sr.getDescription());
							exchange.getMessage().setBody(fault);
							ResponseMessageCollection respColl = exchange.getProperty("prop_response_list",ResponseMessageCollection.class);
							respColl.setFault(fault);
						}
					})
				.end()
				
	 		.endDoTry()
	    	.doCatch(Exception.class)
				.choice()
					.when().simple("${exchangeProperty.CamelExceptionCaught.statusCode} == '504'")
						.log(LoggingLevel.ERROR, "[${exchangeProperty.prop_request_list.msgName}:${exchangeProperty.prop_request_list.requestId}] Corebank TIMEOUT")
					.otherwise()
						.log(LoggingLevel.ERROR, "[${exchangeProperty.prop_request_list.msgName}:${exchangeProperty.prop_request_list.requestId}] Call CB Error.")
						.log(LoggingLevel.ERROR, "${exception.stacktrace}")
				.end()
		    	.process(cbFaultProcessor)
	    	.end()

			.log(LoggingLevel.DEBUG, "komi.debit", "[${exchangeProperty.prop_request_list.msgName}:${exchangeProperty.prop_request_list.requestId}] "
					+ "akan simpan ${exchangeProperty.prop_request_list.msgName}")
			.process(saveCBTransactionProc)
			
			.removeHeaders("cb_*")
		;
		
		
	}

}
