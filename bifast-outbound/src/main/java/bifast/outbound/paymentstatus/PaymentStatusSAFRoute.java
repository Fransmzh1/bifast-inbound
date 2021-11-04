package bifast.outbound.paymentstatus;


import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.jackson.JacksonDataFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import bifast.library.iso20022.custom.BusinessMessage;
import bifast.outbound.corebank.pojo.CbDebitRequestPojo;
import bifast.outbound.paymentstatus.processor.BuildPaymentStatusSAFRequestProcessor;
import bifast.outbound.paymentstatus.processor.PaymentStatusResponseProcessor;
import bifast.outbound.paymentstatus.processor.ProcessQuerySAFProcessor;
import bifast.outbound.paymentstatus.processor.UpdateStatusSAFProcessor;
import bifast.outbound.pojo.RequestMessageWrapper;
import bifast.outbound.service.JacksonDataFormatService;

//@Component
public class PaymentStatusSAFRoute extends RouteBuilder {
	@Autowired
	private BuildPaymentStatusSAFRequestProcessor buildPSRequest;;
	@Autowired
	private PaymentStatusResponseProcessor psResponseProcessor;
	@Autowired
	private UpdateStatusSAFProcessor updateStatusProcessor;
	@Autowired
	private ProcessQuerySAFProcessor processQueryProcessor;
	@Autowired
	private JacksonDataFormatService jdfService;

	@Override
	public void configure() throws Exception {
		
		JacksonDataFormat businessMessageJDF = jdfService.wrapUnwrapRoot(BusinessMessage.class);
		
		from("sql:select ct.id, cht.channel_ref_id, ct.req_bizmsgid, ct.komi_trns_id as komi_id, chnl.channel_type, "
				+ "ct.recpt_bank, cht.request_time  "
				+ "from kc_credit_transfer ct "
				+ "join kc_channel_transaction cht on ct.komi_trns_id = cht.komi_trns_id "
				+ "join kc_channel chnl on chnl.channel_id = cht.channel_id "
				+ " where ct.call_status = 'TIMEOUT'")
			.routeId("komi.ps.saf")
			
			.log("[ChnlReq:${body[channel_ref_id]}] PymtStsSAF started.")
						
			// check settlement dulu
			.process(processQueryProcessor)
			.unmarshal().base64()
			.unmarshal().zipDeflater()
			.log("hasil decrypt: ${body}")
			.unmarshal(businessMessageJDF)
			.process(new Processor() {
				public void process(Exchange exchange) throws Exception {
					BusinessMessage bm = exchange.getMessage().getBody(BusinessMessage.class);
					RequestMessageWrapper rmw = exchange.getMessage().getHeader("hdr_request_list", RequestMessageWrapper.class);
					rmw.setCreditTransferRequest(bm);
					exchange.getMessage().setHeader("hdr_request_list", rmw);
				}
				
			})
			.to("seda:caristtl")
			// selesai check settlement
			

			.process(buildPSRequest)
			
			.to("direct:call-cihub")
			.process(psResponseProcessor)

			.process(updateStatusProcessor)
			.log("status: ${body.psStatus}")

			.filter().simple("${body.psStatus} == 'REJECTED'")
				.log("${body.reqBizmsgid} Rejected")
					//TODO lakukan reversal
				
				//init data reversal dulu
				.process(new Processor() {
					public void process(Exchange exchange) throws Exception {
						CbDebitRequestPojo reversalReq = new CbDebitRequestPojo();
						reversalReq.setAmount(null);
					}
					
				})
				.to("seda:debitreversal")
				
			.end()
			
			.removeHeaders("ps_*")
					
		;

	}

}
