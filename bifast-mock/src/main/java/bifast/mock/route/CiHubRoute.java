package bifast.mock.route;

import org.apache.camel.ExchangePattern;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.jackson.JacksonDataFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.module.jaxb.JaxbAnnotationModule;

import bifast.library.iso20022.custom.BusinessMessage;
import bifast.mock.processor.AccountEnquiryResponseProcessor;
import bifast.mock.processor.CreditTransferResponseProcessor;
import bifast.mock.processor.FICreditTransferResponseProcessor;
import bifast.mock.processor.OnRequestProcessor;
import bifast.mock.processor.PaymentStatusResponseProcessor;
import bifast.mock.processor.ProxyRegistrationResponseProcessor;
import bifast.mock.processor.ProxyResolutionResponseProcessor;
import bifast.mock.processor.RejectMessageProcessor;
import bifast.mock.processor.SettlementProcessor;

@Component
public class CiHubRoute extends RouteBuilder {
	
	@Autowired
	private OnRequestProcessor checkMessageTypeProcessor;
	@Autowired
	private AccountEnquiryResponseProcessor accountEnquiryResponseProcessor;
	@Autowired
	private CreditTransferResponseProcessor creditTransferResponseProcessor;
	@Autowired
	private FICreditTransferResponseProcessor fICreditTransferResponseProcessor;
	@Autowired
	private PaymentStatusResponseProcessor paymentStatusResponseProcessor;
	@Autowired
	private RejectMessageProcessor rejectMessageProcessor;
	@Autowired
	private ProxyRegistrationResponseProcessor proxyRegistrationResponseProcessor;
	@Autowired
	private ProxyResolutionResponseProcessor proxyResolutionResponseProcessor;
	@Autowired
	private SettlementProcessor settlementProcessor;
	
	JacksonDataFormat jsonBusinessMessageDataFormat = new JacksonDataFormat(BusinessMessage.class);

	private void configureJson() {
		jsonBusinessMessageDataFormat.addModule(new JaxbAnnotationModule());  //supaya nama element pake annot JAXB (uppercasecamel)
		jsonBusinessMessageDataFormat.enableFeature(DeserializationFeature.UNWRAP_ROOT_VALUE);
		jsonBusinessMessageDataFormat.enableFeature(SerializationFeature.WRAP_ROOT_VALUE);
		jsonBusinessMessageDataFormat.setInclude("NON_NULL");
		jsonBusinessMessageDataFormat.setInclude("NON_EMPTY");
	}


	@Override
	public void configure() throws Exception {

		configureJson();
		
		restConfiguration()
			.component("servlet")
		;
		
		rest("/")
			.post("/cihub")
				.consumes("application/json")
				.to("direct:receive")
			.post("/cihub-proxy-regitrastion")
				.description("Pengiriman instruksi Proxy Registration BI-FAST")
				.consumes("application/json")
				.to("direct:proxyRegistration")
		;
	

		from("direct:receive").routeId("receive")

			.setExchangePattern(ExchangePattern.InOut)
			.convertBodyTo(String.class)
			.log("Terima di mock")
			.log("${body}")
			.log("end-delay")

			.unmarshal(jsonBusinessMessageDataFormat)
			.setHeader("objRequest", simple("${body}"))
			
			.process(checkMessageTypeProcessor)
			.log("${header.msgType}")

			.setHeader("delay", simple("${random(100,1000)}"))
			.choice()
				.when(simple("${header.msgType} == 'PaymentStatusRequest'"))
					.setHeader("delay", constant(500))
			.end()
			.delay(simple("${header.delay}"))

			.choice()
				.when().simple("${header.msgType} == 'AccountEnquiryRequest'")
					.process(accountEnquiryResponseProcessor)
					
				.when().simple("${header.msgType} == 'CreditTransferRequest'")
					.process(creditTransferResponseProcessor)
					// .log("akan seda settlement")
					.setExchangePattern(ExchangePattern.InOnly)
					.to("seda:settlement")

				.when().simple("${header.msgType} == 'FICreditTransferRequest'")
					.process(fICreditTransferResponseProcessor)
					.to("seda:settlement&exchangePattern=InOnly")

				.when().simple("${header.msgType} == 'PaymentStatusRequest'")
					.log("Akan proses Payment Status")
					.process(paymentStatusResponseProcessor)
					.unmarshal().base64()
					.unmarshal().zipDeflater()
					.unmarshal(jsonBusinessMessageDataFormat)

				.when().simple("${header.msgType} == 'ReverseCreditTransferRequest'")
					.process(creditTransferResponseProcessor)

				.when().simple("${header.msgType} == 'ProxyRegistrationRequest'")
					.process(proxyRegistrationResponseProcessor)

				.when().simple("${header.msgType} == 'ProxyResolutionRequest'")
					.log("ProxyResolutionRequest")
					.process(proxyResolutionResponseProcessor)
					.unmarshal(jsonBusinessMessageDataFormat)
				.otherwise()	
					.log("Other message")
			.end()
				
			// .process(rejectMessageProcessor)
			.marshal(jsonBusinessMessageDataFormat)  // remark bila rejection

			// .process(proxyResolutionResponseProcessor)

			.log("Response dari mock")
			.log("${body}")
			.log("delay selama ${header.delay}s")
			.removeHeader("msgType")
			.removeHeader("delay")
			.removeHeader("objRequest")
		;
		
		from("direct:proxyRegistration").routeId("proxyRegistration")

			.setExchangePattern(ExchangePattern.InOut)
			.convertBodyTo(String.class)
			.log("Terima di mock")
			.log("${body}")
			.delay(1000)
			.log("end-delay")
			.unmarshal(jsonBusinessMessageDataFormat)
			.process(proxyRegistrationResponseProcessor)
			.marshal(jsonBusinessMessageDataFormat)  // remark bila rejection
			.log("Response dari mock")
			.log("${body}")
			.removeHeader("msgType")
			
		;


		from("seda:settlement")
			.log("Proses Settlement")
			.delay(500)
			.process(settlementProcessor)
			.marshal(jsonBusinessMessageDataFormat)
			.log("${body}")
			.to("rest:post:inbound?host=localhost:9001/services/api&"
					+ "bridgeEndpoint=true")

//			.setHeader("HttpMethod", constant("POST"))
//			.enrich("http:localhost:9001/services/api/inbound"
//					+ "bridgeEndpoint=true",
//					enrichmentAggregator)
//			.convertBodyTo(String.class)

		;
		
	}

}
