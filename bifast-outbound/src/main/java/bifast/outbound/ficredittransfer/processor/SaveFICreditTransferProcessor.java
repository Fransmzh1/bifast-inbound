package bifast.outbound.ficredittransfer.processor;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import bifast.library.iso20022.custom.BusinessMessage;
import bifast.library.iso20022.pacs009.CreditTransferTransaction44;
import bifast.outbound.ficredittransfer.ChnlFICreditTransferRequestPojo;
import bifast.outbound.model.FICreditTransfer;
import bifast.outbound.repository.FICreditTransferRepository;

@Component
public class SaveFICreditTransferProcessor implements Processor {
	@Autowired
	private FICreditTransferRepository fiCreditTransferRepo;
	
	@Override
	public void process(Exchange exchange) throws Exception {

		System.out.println("Mulai");
		
		BusinessMessage biReqBM = exchange.getMessage().getHeader("fict_objreqbi", BusinessMessage.class);
		CreditTransferTransaction44 ctRequest = biReqBM.getDocument().getFiCdtTrf().getCdtTrfTxInf().get(0);

		String strCihubRequestTime = exchange.getMessage().getHeader("fict_cihubRequestTime", String.class);
		String strCihubResponseTime = exchange.getMessage().getHeader("fict_cihubResponseTime", String.class);
		
		String encrRequestMesg = exchange.getMessage().getHeader("fict_encr_request", String.class);
		String encrResponseMesg = exchange.getMessage().getHeader("fict_encr_response", String.class);
		
		
		ChnlFICreditTransferRequestPojo chnRequest = exchange.getMessage().getHeader("hdr_channelRequest", ChnlFICreditTransferRequestPojo.class);
		FICreditTransfer ct = new FICreditTransfer();
		
		ct.setAmount(ctRequest.getIntrBkSttlmAmt().getValue());
		
		
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyyMMdd HH:mm:ss");

		LocalDateTime cihubRequestTime = LocalDateTime.parse(strCihubRequestTime, dtf);
		LocalDateTime cihubResponseTime = LocalDateTime.parse(strCihubResponseTime, dtf);

		ct.setCihubRequestDT(cihubRequestTime);
		ct.setCihubResponseDT(cihubResponseTime);
	
		
		ct.setCreditBic(ctRequest.getCdtr().getFinInstnId().getOthr().getId());
		
		ct.setCreDt(biReqBM.getDocument().getFiCdtTrf().getGrpHdr().getCreDtTm().toGregorianCalendar().toZonedDateTime().toLocalDateTime());

		ct.setDebtorBic(ctRequest.getDbtr().getFinInstnId().getOthr().getId());
		
		ct.setFullRequestMessage(encrRequestMesg);
		ct.setFullResponseMsg(encrResponseMesg);
		
		ct.setIntrRefId(chnRequest.getIntrnRefId());
		ct.setOriginatingBank(biReqBM.getAppHdr().getFr().getFIId().getFinInstnId().getOthr().getId());
		ct.setRequestBizMsgIdr(biReqBM.getAppHdr().getBizMsgIdr());
		
		BusinessMessage biResponseBM = exchange.getMessage().getHeader("fict_objresponsebi", BusinessMessage.class);

		if (!(null== biResponseBM)) {
			ct.setResponseBizMsgIdr(biResponseBM.getAppHdr().getBizMsgIdr());
			ct.setResponseStatus(biResponseBM.getDocument().getFiToFIPmtStsRpt().getTxInfAndSts().get(0).getTxSts());
			ct.setCallStatus("SUCCESS");
		}
		
		String errorStatus = exchange.getMessage().getHeader("hdr_error_status", String.class);
		if (!(null==errorStatus))
			ct.setCallStatus(errorStatus);
		
		fiCreditTransferRepo.save(ct);
		
	}

}
