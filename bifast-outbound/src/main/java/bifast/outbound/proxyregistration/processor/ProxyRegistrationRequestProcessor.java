package bifast.outbound.proxyregistration.processor;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

import bifast.library.iso20022.custom.BusinessMessage;
import bifast.library.iso20022.custom.Document;
import bifast.library.iso20022.head001.BusinessApplicationHeaderV01;
import bifast.library.iso20022.service.AppHeaderService;
import bifast.library.iso20022.service.Proxy001MessageService;
import bifast.library.iso20022.service.Proxy001Seed;
import bifast.outbound.config.Config;
import bifast.outbound.proxyregistration.ChnlProxyRegistrationRequestPojo;
import bifast.outbound.service.UtilService;

@Component
@ComponentScan(basePackages = {"bifast.library.iso20022.service", "bifast.library.config"} )
public class ProxyRegistrationRequestProcessor implements Processor {

	@Autowired
	private Config config;
	@Autowired
	private AppHeaderService appHeaderService;
	@Autowired
	private Proxy001MessageService proxy001MessageService;
	@Autowired
	private UtilService utilService;

	@Override
	public void process(Exchange exchange) throws Exception {

		ChnlProxyRegistrationRequestPojo chnReq = exchange.getIn().getBody(ChnlProxyRegistrationRequestPojo.class);

		BusinessApplicationHeaderV01 hdr = new BusinessApplicationHeaderV01();
		
		String trxType = "710";
		String bizMsgId = utilService.genOfiBusMsgId(trxType, chnReq.getChannel());
		String msgId = utilService.genMessageId(trxType);
		
		hdr = appHeaderService.getAppHdr(config.getBicode(), "prxy.001.001.01", bizMsgId);
		
		Proxy001Seed seedProxyRegis = new Proxy001Seed();
		
		seedProxyRegis.setMsgId(msgId);
//		seedProxyRegis.setBizMsgId(hdr.getBizMsgIdr());
		seedProxyRegis.setRegistrationType(chnReq.getRegistrationType());
		seedProxyRegis.setProxyType(chnReq.getProxyType());
		seedProxyRegis.setProxyValue(chnReq.getProxyValue());
		seedProxyRegis.setRegisterDisplayName(chnReq.getRegisterDisplayName());
		seedProxyRegis.setRegisterAccountType(chnReq.getRegisterAccountType());
		seedProxyRegis.setRegisterAccountName(chnReq.getRegisterAccountName());
		seedProxyRegis.setRegisterAccountNumber(chnReq.getRegisterAccountNumber());
		seedProxyRegis.setRegisterSecondIdType(chnReq.getRegisterSecondIdType());
		seedProxyRegis.setRegisterSecondIdValue(chnReq.getRegisterSecondIdValue());
		seedProxyRegis.setCustomerType(chnReq.getCustomerType());
		seedProxyRegis.setCustomerId(chnReq.getCustomerId());
		seedProxyRegis.setResidentialStatus(chnReq.getResidentialStatus());
		seedProxyRegis.setTownName(chnReq.getTownName());
		
		Document doc = new Document();
		doc.setPrxyRegn(proxy001MessageService.proxyRegistrationRequest(seedProxyRegis));

		BusinessMessage busMsg = new BusinessMessage();
		busMsg.setAppHdr(hdr);
		busMsg.setDocument(doc);
	
		exchange.getIn().setBody(busMsg);
		
	}

}
