package bifast.mock.inbound;

import java.math.BigDecimal;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import bifast.library.iso20022.custom.BusinessMessage;
import bifast.library.iso20022.custom.Document;
import bifast.library.iso20022.head001.BusinessApplicationHeaderV01;
import bifast.library.iso20022.service.Pacs008MessageService;
import bifast.library.iso20022.service.Pacs008Seed;
import bifast.mock.inbound.pojo.AERequestPojo;
import bifast.mock.inbound.pojo.PaymentRequestPojo;
import bifast.mock.isoservice.ApplHeaderService;
import bifast.mock.processor.UtilService;


@Component
public class BuildAERequestProcessor implements Processor {
	@Autowired private ApplHeaderService appHeaderService;
	@Autowired private Pacs008MessageService pacs008MessageService;
	@Autowired private UtilService utilService;

	@Override
	public void process(Exchange exchange) throws Exception {

		AERequestPojo req = exchange.getMessage().getBody(AERequestPojo.class);
		
		String msgType = "510";
		String bizMsgId = utilService.genRfiBusMsgId("510", "01", "BMNDIDJA");
		String msgId = utilService.genMessageId("510", "BMNDIDJA");
		
		BusinessApplicationHeaderV01 hdr = new BusinessApplicationHeaderV01();

		hdr = appHeaderService.getAppHdr("BMNDIDJA", "SIHBIDJ1", "pacs.008.001.08", bizMsgId);

		Pacs008Seed seedAcctEnquiry = new Pacs008Seed();
		
		seedAcctEnquiry.setMsgId(msgId);
		seedAcctEnquiry.setBizMsgId(hdr.getBizMsgIdr());
		
		seedAcctEnquiry.setAmount(new BigDecimal(req.getAmount()));
		
		seedAcctEnquiry.setCategoryPurpose("01");
		seedAcctEnquiry.setCrdtAccountNo(req.getCreditorAccountNo());
		seedAcctEnquiry.setOrignBank("BMNDIDJA");
		seedAcctEnquiry.setRecptBank("SIHBIDJ1");
		seedAcctEnquiry.setTrnType("510");

		Document doc = new Document();
		doc.setFiToFICstmrCdtTrf(pacs008MessageService.accountEnquiryRequest(seedAcctEnquiry));

		BusinessMessage busMsg = new BusinessMessage();
		busMsg.setAppHdr(hdr);
		busMsg.setDocument(doc);
	
		exchange.getIn().setBody(busMsg);
	}

}
