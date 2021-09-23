package bifast.outbound.proxyregistration.processor;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import bifast.library.iso20022.custom.BusinessMessage;
import bifast.library.iso20022.custom.Document;
import bifast.library.iso20022.head001.BusinessApplicationHeaderV01;
import bifast.library.iso20022.prxy003.ProxyLookUpType1Code;
import bifast.library.iso20022.service.AppHeaderService;
import bifast.library.iso20022.service.Proxy003MessageService;
import bifast.library.iso20022.service.Proxy003Seed;
import bifast.outbound.config.Config;
import bifast.outbound.processor.UtilService;
import bifast.outbound.proxyregistration.ChnlProxyResolutionRequestPojo;

@Component
public class ProxyResolutionRequestProcessor implements Processor {

	@Autowired
	private Config config;
	@Autowired
	private AppHeaderService appHeaderService;
	@Autowired
	private Proxy003MessageService proxy003MessageService;
	@Autowired
	private UtilService utilService;

	@Override
	public void process(Exchange exchange) throws Exception {

		ChnlProxyResolutionRequestPojo chnReq = exchange.getIn().getBody(ChnlProxyResolutionRequestPojo.class);

		BusinessApplicationHeaderV01 hdr = new BusinessApplicationHeaderV01();

		String trxType = "610";
		String bizMsgId = utilService.genOfiBusMsgId(trxType, chnReq.getChannel());
		String msgId = utilService.genMessageId(trxType);
		
		hdr = appHeaderService.getAppHdr(config.getBicode(), "prxy.003.001.01", bizMsgId);
		
		Proxy003Seed seedProxyResolution = new Proxy003Seed();
		
		seedProxyResolution.setMsgId(msgId);
		seedProxyResolution.setTrnType(trxType);
		
		if (chnReq.getLookupType().equals("PXRS")) seedProxyResolution.setLookupType(ProxyLookUpType1Code.PXRS);
		else if (chnReq.getLookupType().equals("CHCK")) seedProxyResolution.setLookupType(ProxyLookUpType1Code.CHCK);
		else if (chnReq.getLookupType().equals("NMEQ")) seedProxyResolution.setLookupType(ProxyLookUpType1Code.NMEQ);

		seedProxyResolution.setProxyType(chnReq.getProxyType());
		seedProxyResolution.setProxyValue(chnReq.getProxyValue());
		
		Document doc = new Document();
		doc.setPrxyLookUp(proxy003MessageService.proxyResolutionRequest(seedProxyResolution));

		BusinessMessage busMsg = new BusinessMessage();
		busMsg.setAppHdr(hdr);
		busMsg.setDocument(doc);
	
		exchange.getIn().setBody(busMsg);
	}

}
