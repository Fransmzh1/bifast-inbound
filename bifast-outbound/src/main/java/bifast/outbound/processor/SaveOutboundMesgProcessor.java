package bifast.outbound.processor;

import java.time.LocalDateTime;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import bifast.library.iso20022.custom.BusinessMessage;
import bifast.library.model.OutboundMessage;
import bifast.library.repository.OutboundMessageRepository;

@Component
public class SaveOutboundMesgProcessor implements Processor {

	@Autowired
	private OutboundMessageRepository outboundRepo;

	@Override
	public void process(Exchange exchange) throws Exception {

		String jsonRequest = exchange.getMessage().getHeader("req_jsonbi", String.class);
		BusinessMessage objRequest = exchange.getMessage().getHeader("req_objbi", BusinessMessage.class);

		String jsonResponse = exchange.getMessage().getHeader("resp_jsonbi", String.class);
		BusinessMessage objResponse = exchange.getMessage().getHeader("resp_objbi", BusinessMessage.class);
		
		OutboundMessage outboundMessage = new OutboundMessage();
		
		outboundMessage.setBizMsgIdr(objRequest.getAppHdr().getBizMsgIdr());
		outboundMessage.setFullMessage(jsonRequest);
		outboundMessage.setMsgDefIdr(objRequest.getAppHdr().getMsgDefIdr());
		outboundMessage.setSendDt(LocalDateTime.now());
		outboundMessage.setToFinId(objRequest.getAppHdr().getTo().getFIId().getFinInstnId().getOthr().getId());
		outboundMessage.setResponseMessage(jsonResponse);
		outboundMessage.setRespBizMsgId(objResponse.getAppHdr().getBizMsgIdr());
		
		outboundMessage.setHttpResponse("200");
		
		outboundRepo.save(outboundMessage);

	}

}
