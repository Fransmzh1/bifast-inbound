package bifast.inbound.processor;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import bifast.library.iso20022.custom.BusinessMessage;
import bifast.library.model.DomainCode;
import bifast.library.repository.DomainCodeRepository;

@Component
public class CheckMessageTypeProcessor implements Processor{

	@Autowired
	private DomainCodeRepository domainCodeRepo;
	
	@Override
	public void process(Exchange exchange) throws Exception {

		BusinessMessage inputMsg = exchange.getMessage().getBody(BusinessMessage.class);
		String msgIdr = inputMsg.getAppHdr().getMsgDefIdr();
		String trnType = inputMsg.getAppHdr().getBizMsgIdr().substring(16,19);
		
//		String msgType = "";
//		
		if (msgIdr.startsWith("pacs.002")) {
			trnType = "SETTLEMENT";
		}
//		
//		DomainCode domainCode = domainCodeRepo.findByGrpAndKey("TRANSACTION.TYPE", trnType).orElse(new DomainCode());
//		msgType = domainCode.getValue();

		exchange.getMessage().setHeader("hdr_msgType", trnType);
		
	}
	
}
