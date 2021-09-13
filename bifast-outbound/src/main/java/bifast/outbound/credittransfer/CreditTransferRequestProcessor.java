package bifast.outbound.credittransfer;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import bifast.library.iso20022.custom.BusinessMessage;
import bifast.library.iso20022.custom.Document;
import bifast.library.iso20022.head001.BusinessApplicationHeaderV01;
import bifast.library.iso20022.service.AppHeaderService;
import bifast.library.iso20022.service.Pacs008MessageService;
import bifast.library.iso20022.service.Pacs008Seed;
import bifast.outbound.config.Config;
import bifast.outbound.processor.UtilService;

@Component
public class CreditTransferRequestProcessor implements Processor {

	@Autowired
	private Config config;
	@Autowired
	private AppHeaderService appHeaderService;
	@Autowired
	private Pacs008MessageService pacs008MessageService;
	@Autowired
	private UtilService utilService;

	@Override
	public void process(Exchange exchange) throws Exception {

		ChnlCreditTransferRequestPojo chnReq = exchange.getIn().getHeader("hdr_channelRequest",ChnlCreditTransferRequestPojo.class);
		
		BusinessApplicationHeaderV01 hdr = new BusinessApplicationHeaderV01();
		String bizMsgId = utilService.genOfiBusMsgId("010", chnReq.getChannel());
		String msgId = utilService.genMessageId("010");

		hdr = appHeaderService.getAppHdr(chnReq.getRecptBank(), "pacs.008.001.08", bizMsgId);

		Pacs008Seed seedCreditTrn = new Pacs008Seed();
		seedCreditTrn.setMsgId(msgId);
		seedCreditTrn.setAmount(chnReq.getAmount());
		seedCreditTrn.setBizMsgId(hdr.getBizMsgIdr());
		seedCreditTrn.setCategoryPurpose(chnReq.getCategoryPurpose());
		
		seedCreditTrn.setChannel(chnReq.getChannel());
		
		seedCreditTrn.setCrdtAccountNo(chnReq.getCrdtAccountNo());		
		seedCreditTrn.setCrdtAccountType(chnReq.getCrdtAccountType());
		seedCreditTrn.setCrdtId(chnReq.getCrdtId());
		seedCreditTrn.setCrdtName(chnReq.getCrdtName());
		seedCreditTrn.setCrdtIdType(chnReq.getCrdtIdType());
		
		seedCreditTrn.setDbtrAccountNo(chnReq.getDbtrAccountNo());
		seedCreditTrn.setDbtrAccountType(chnReq.getDbtrAccountType());
		seedCreditTrn.setDbtrName(chnReq.getDbtrName());
		seedCreditTrn.setDbtrId(chnReq.getDbtrId());
		seedCreditTrn.setDbtrIdType(chnReq.getDbtrIdType());
		seedCreditTrn.setOrignBank(config.getBankcode());
		seedCreditTrn.setPaymentInfo(chnReq.getPaymentInfo());
		seedCreditTrn.setRecptBank(chnReq.getRecptBank());
		seedCreditTrn.setTrnType("010");
		
		if (!(null == chnReq.getCrdtProxyIdType())) {
			seedCreditTrn.setCrdtProxyIdType(chnReq.getCrdtProxyIdType());
			seedCreditTrn.setCrdtProxyIdValue(chnReq.getCrdtProxyIdValue());
		}
			
		Document doc = new Document();
		doc.setFiToFICstmrCdtTrf(pacs008MessageService.creditTransferRequest(seedCreditTrn));

		BusinessMessage busMsg = new BusinessMessage();
		busMsg.setAppHdr(hdr);
		busMsg.setDocument(doc);

		exchange.getIn().setBody(busMsg);

	}

}
