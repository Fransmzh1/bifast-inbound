package bifast.outbound.processor;

import java.time.Instant;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import bifast.outbound.model.Channel;
import bifast.outbound.pojo.RequestMessageWrapper;
import bifast.outbound.repository.ChannelRepository;
import bifast.outbound.service.UtilService;

@Component
public class InitRequestMessageWrapperProcessor implements Processor {
	@Autowired
	private ChannelRepository channelRepo;
	@Autowired
	private UtilService utilService;

	@Override
	public void process(Exchange exchange) throws Exception {
		String clientId = exchange.getMessage().getHeader("clientId", String.class);
		Channel channel = channelRepo.findById(clientId).orElse(new Channel());
		String komiTrnsId = utilService.genKomiTrnsId();
		RequestMessageWrapper rmw = new RequestMessageWrapper();
		
		rmw.setChannelId(clientId);
		rmw.setChannelType(channel.getChannelType());
		
		rmw.setMerchantType(channel.getMerchantCode());
		rmw.setKomiTrxId(komiTrnsId);
//		rmw.setRequestTime(LocalDateTime.now());
		rmw.setKomiStart(Instant.now());
		
		exchange.getMessage().setHeader("hdr_request_list", rmw);
	}

}
