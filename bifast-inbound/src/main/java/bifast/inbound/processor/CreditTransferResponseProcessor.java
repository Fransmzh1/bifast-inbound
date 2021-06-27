package bifast.inbound.processor;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import bifast.library.iso20022.custom.BusinessMessage;
import bifast.library.iso20022.custom.Document;
import bifast.library.iso20022.head001.BusinessApplicationHeaderV02;
import bifast.library.iso20022.pacs002.FIToFIPaymentStatusReportV11;
import bifast.library.iso20022.service.AppHeaderService;
import bifast.library.iso20022.service.Pacs002MessageService;
import bifast.library.iso20022.service.Pacs002Seed;

@Component
public class CreditTransferResponseProcessor implements Processor {

	@Autowired
	private AppHeaderService appHdrService;
	@Autowired
	private Pacs002MessageService pacs002Service;

	@Override
	public void process(Exchange exchange) throws Exception {

		BusinessMessage reqBusMesg = exchange.getMessage().getHeader("rcv_obj", BusinessMessage.class);

		// TODO cek account ke core banking

		Pacs002Seed resp = new Pacs002Seed();
		
		resp.setCreditorName("UJANG");
		resp.setCreditorResidentialStatus("01");  // 01 RESIDENT
		resp.setCreditorTown("0300");  
		resp.setStatus("ACTC"); 
		resp.setReason("U001");     
		resp.setAdditionalInfo("Ini informasi tambahan aja.");
		resp.setCreditorType("01");
		resp.setCreditorId("234433");
		
		FIToFIPaymentStatusReportV11 respMsg = pacs002Service.creditTransferRequestResponse(resp, reqBusMesg);
		Document doc = new Document();
		doc.setFiToFIPmtStsRpt(respMsg);
		
		String orignBank = reqBusMesg.getAppHdr().getFr().getFIId().getFinInstnId().getOthr().getId();
		BusinessApplicationHeaderV02 appHdr = appHdrService.initAppHdr(orignBank, "pacs002.001.10", "010", "99");

		BusinessMessage respBusMesg = new BusinessMessage();
		respBusMesg.setAppHdr(appHdr);
		respBusMesg.setDocument(doc);
		
		exchange.getIn().setBody(respBusMesg);

	}

}
