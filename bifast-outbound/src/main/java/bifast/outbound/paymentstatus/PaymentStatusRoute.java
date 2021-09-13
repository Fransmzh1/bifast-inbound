package bifast.outbound.paymentstatus;

import java.net.SocketTimeoutException;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.jackson.JacksonDataFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.module.jaxb.JaxbAnnotationModule;

import bifast.library.iso20022.custom.BusinessMessage;
import bifast.outbound.processor.EnrichmentAggregator;

@Component
public class PaymentStatusRoute extends RouteBuilder {

	@Autowired
	private EnrichmentAggregator enrichmentAggregator;
	@Autowired
	private SavePSTablesProcessor savePSTableProcessor;
	@Autowired
	private PaymentStatusRequestProcessor paymentStatusRequestProcessor;
//	@Autowired
//	private PaymentStatusResponseProcessor paymentStatusResponseProcessor;
//	@Autowired
//	private FaultProcessor faultProcessor;

	JacksonDataFormat PaymentStatusRequestJDF = new JacksonDataFormat(ChnlPaymentStatusRequestPojo.class);
	JacksonDataFormat PaymentStatusResponseJDF = new JacksonDataFormat(ChnlPaymentStatusResponsePojo.class);
	JacksonDataFormat jsonBusinessMessageFormat = new JacksonDataFormat(BusinessMessage.class);

	@Override
	public void configure() throws Exception {

		PaymentStatusRequestJDF.setInclude("NON_NULL");
		PaymentStatusRequestJDF.setInclude("NON_EMPTY");
		
		PaymentStatusResponseJDF.addModule(new JaxbAnnotationModule());  //supaya nama element pake annot JAXB (uppercasecamel)
		PaymentStatusResponseJDF.setInclude("NON_NULL");
		PaymentStatusResponseJDF.setInclude("NON_EMPTY");

		jsonBusinessMessageFormat.addModule(new JaxbAnnotationModule());  //supaya nama element pake annot JAXB (uppercasecamel)
		jsonBusinessMessageFormat.setInclude("NON_NULL");
		jsonBusinessMessageFormat.setInclude("NON_EMPTY");
		jsonBusinessMessageFormat.enableFeature(SerializationFeature.WRAP_ROOT_VALUE);
		jsonBusinessMessageFormat.enableFeature(DeserializationFeature.UNWRAP_ROOT_VALUE);

		
		from ("direct:paymentstatus")
		
			// cek settlement dulu
			.log("di direct:paymentstatus")
			.setHeader("ps_pymtstsreq", simple("${body}"))
			.process(paymentStatusRequestProcessor)
			
			.setHeader("ps_objreqbi", simple("${body}"))

			.marshal(jsonBusinessMessageFormat)
			.log("PS data : ${body}")
			
			.setHeader("ps_status", simple("New"))
			.to("seda:encryptPSbody")
			.to("seda:savePStables")

			.setHeader("ps_cihubRequestTime", simple("${date:now:yyyyMMdd hh:mm:ss}"))
			.doTry()
//				
				.setHeader("HttpMethod", constant("POST"))
				.enrich("http:{{bifast.ciconnector-url}}?"
						+ "socketTimeout={{bifast.timeout}}&" 
						+ "bridgeEndpoint=true",
						enrichmentAggregator)
				.convertBodyTo(String.class)

				.log("akan save awal PS")
				.to("seda:encryptPSbody")

//				.setHeader("hdr_fullresponsemessage", simple("${body}"))
//
				.setHeader("ps_cihubResponseTime", simple("${date:now:yyyyMMdd hh:mm:ss}"))
				.setHeader("ps_status", simple("Received"))

				.unmarshal(jsonBusinessMessageFormat)
				.setHeader("ps_objresponsebi", simple("${body}"))
				.marshal(jsonBusinessMessageFormat)
				
			.doCatch(SocketTimeoutException.class)     // klo timeout maka kirim payment status
				.setHeader("ps_status", simple("Timeout"))
		    	.setBody(constant(null))
				
			.end()
			
			.log("selesai dotry PS")
			.setHeader("ps_channelResponseTime", simple("${date:now:yyyyMMdd hh:mm:ss}"))

			.to("seda:savePStables")

			.removeHeaders("ps_*")
		;
			
		from("seda:encryptPSbody")
			.setHeader("ps_tmp", simple("${body}"))
			.marshal().zipDeflater()
			.marshal().base64()
			.setHeader("ps_encrMessage", simple("${body}"))
			.setBody(simple("${header.ps_tmp}"))
		;
		from("seda:savePStables")
			.process(savePSTableProcessor)
		;

	}

}
