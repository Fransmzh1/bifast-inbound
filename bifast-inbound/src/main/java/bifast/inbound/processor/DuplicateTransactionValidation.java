package bifast.inbound.processor;

import java.util.List;
import java.util.Optional;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import bifast.inbound.exception.DuplicateTransactionException;
import bifast.inbound.model.CreditTransfer;
import bifast.inbound.pojo.ProcessDataPojo;
import bifast.inbound.pojo.flat.FlatPacs008Pojo;
import bifast.inbound.repository.CreditTransferRepository;
import bifast.inbound.service.UtilService;
import bifast.library.iso20022.custom.BusinessMessage;
import bifast.library.iso20022.custom.Document;
import bifast.library.iso20022.head001.BusinessApplicationHeaderV01;
import bifast.library.iso20022.pacs002.FIToFIPaymentStatusReportV10;
import bifast.library.iso20022.service.AppHeaderService;
import bifast.library.iso20022.service.Pacs002MessageService;
import bifast.library.iso20022.service.Pacs002Seed;

@Component
public class DuplicateTransactionValidation implements Processor{
	@Autowired private CreditTransferRepository ctRepo;
	@Autowired private AppHeaderService hdrService;
	@Autowired private Pacs002MessageService pacs002Service;
	@Autowired private UtilService utilService;

	@Override
	public void process(Exchange exchange) throws Exception {

		ProcessDataPojo processData = exchange.getMessage().getHeader("hdr_process_data", ProcessDataPojo.class);

		FlatPacs008Pojo flat008 = (FlatPacs008Pojo)processData.getBiRequestFlat();
		System.out.println("Cari " + flat008.getBizMsgIdr());
		List<CreditTransfer> lCreditTransfer = ctRepo.findAllByCrdtTrnRequestBizMsgIdr(flat008.getBizMsgIdr());	

		String saf = Optional.ofNullable(flat008.getCpyDplct()).orElse("");

			if ((lCreditTransfer.size()>0) && (!(saf.equals("DUPL")))) {
				BusinessMessage response = ctResponse (processData);
				exchange.getMessage().setBody(response);
	
				exchange.getMessage().setBody(response);
	
				processData.setBiResponseMsg(response);
				exchange.getMessage().setHeader("hdr_process_data", processData);
				throw new DuplicateTransactionException("Nomor BizMsgIdr duplikat");
			}
	}
	
	private BusinessMessage ctResponse (ProcessDataPojo processData) throws Exception {
		FlatPacs008Pojo request = (FlatPacs008Pojo) processData.getBiRequestFlat();

		BusinessMessage bmRequest = processData.getBiRequestMsg();
		String komiTrnsId = processData.getKomiTrnsId();
		
		String msgType = request.getBizMsgIdr().substring(16, 19);
		String bizMsgId = utilService.genRfiBusMsgId(msgType, komiTrnsId);
		String msgId = utilService.genMsgId(msgType, komiTrnsId);

		Pacs002Seed seed = new Pacs002Seed();
		
		seed.setAdditionalInfo("Nomor BizMsgIdr duplikat");
		seed.setCreditorAccountIdType(request.getCreditorAccountType());
		seed.setCreditorAccountNo(request.getCreditorAccountNo());
		
		if (null != request.getCreditorPrvId())
			seed.setCreditorId(request.getCreditorPrvId());
		else 
			seed.setCreditorId(request.getCreditorOrgId());
		
		seed.setCreditorName(request.getCreditorName());
		seed.setCreditorResidentialStatus(request.getCreditorResidentialStatus());
		seed.setCreditorTown(request.getCreditorTownName());
		seed.setCreditorType(request.getCreditorType());
		seed.setMsgId(msgId);
		seed.setReason("62");
		seed.setStatus("RJCT");

		BusinessApplicationHeaderV01 hdr = new BusinessApplicationHeaderV01();
		hdr = hdrService.getAppHdr(request.getFrBic(), "pacs.002.001.10", bizMsgId);

		BusinessMessage bmResponse = new BusinessMessage();
		bmResponse.setAppHdr(hdr);
		
		Document doc = new Document();
		
		FIToFIPaymentStatusReportV10 ctRespDoc = pacs002Service.creditTransferRequestResponse(seed, bmRequest);

		doc.setFiToFIPmtStsRpt(ctRespDoc);
		bmResponse.setDocument(doc);
		
		return bmResponse;
	}
	

}
