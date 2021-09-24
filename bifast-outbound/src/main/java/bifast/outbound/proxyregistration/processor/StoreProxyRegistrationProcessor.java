package bifast.outbound.proxyregistration.processor;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import bifast.library.iso20022.custom.BusinessMessage;
import bifast.library.iso20022.prxy001.ProxyRegistrationV01;
import bifast.outbound.model.ProxyMessage;
import bifast.outbound.proxyregistration.ChnlProxyRegistrationRequestPojo;
import bifast.outbound.repository.ProxyMessageRepository;

@Component
public class StoreProxyRegistrationProcessor implements Processor{

	@Autowired
	private ProxyMessageRepository proxyMessageRepo;

	@Override
	public void process(Exchange exchange) throws Exception {

		BusinessMessage proxyRequest = exchange.getMessage().getHeader("prx_birequest", BusinessMessage.class);
		ProxyRegistrationV01 regRequest = proxyRequest.getDocument().getPrxyRegn();
		
		ProxyMessage proxyMessage = new ProxyMessage();
		
		Long chnlTrxId = exchange.getMessage().getHeader("hdr_chnlTable_id", Long.class);
		if (!(null == chnlTrxId))
			proxyMessage.setChnlTrxId(chnlTrxId);

		proxyMessage.setAccountName(regRequest.getRegn().getPrxyRegn().getAcct().getNm());
		proxyMessage.setAccountNumber(regRequest.getRegn().getPrxyRegn().getAcct().getId().getOthr().getId());
		proxyMessage.setAccountType(regRequest.getRegn().getPrxyRegn().getAcct().getTp().getPrtry());
		proxyMessage.setCustomerId(regRequest.getSplmtryData().get(0).getEnvlp().getCstmr().getId());
		proxyMessage.setCustomerType(regRequest.getSplmtryData().get(0).getEnvlp().getCstmr().getTp());

		proxyMessage.setDisplayName(regRequest.getRegn().getPrxyRegn().getDsplNm());
		
		String encrRequestMesg = exchange.getMessage().getHeader("hdr_encr_request", String.class);
		String encrResponseMesg = exchange.getMessage().getHeader("hdr_encr_response", String.class);
		
		proxyMessage.setFullRequestMesg(encrRequestMesg);
		proxyMessage.setFullResponseMesg(encrResponseMesg);

		ChnlProxyRegistrationRequestPojo channelRequest = exchange.getMessage().getHeader("hdr_channelRequest", ChnlProxyRegistrationRequestPojo.class);

		proxyMessage.setIntrnRefId(channelRequest.getOrignReffId());
		
		proxyMessage.setOperationType(channelRequest.getRegnTp());
		proxyMessage.setProxyType(channelRequest.getProxyTp());
		proxyMessage.setProxyValue(channelRequest.getProxyVal());
		
		proxyMessage.setResidentStatus(regRequest.getSplmtryData().get(0).getEnvlp().getCstmr().getRsdntSts());

		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyyMMdd HH:mm:ss");

		String strPrxyReqTime = exchange.getMessage().getHeader("hdr_cihubRequestTime", String.class);
		String strPrxyRespTime = exchange.getMessage().getHeader("hdr_cihubResponseTime", String.class);
	
		LocalDateTime prxyRequestTime = LocalDateTime.parse(strPrxyReqTime, dtf);
		
		LocalDateTime prxyResponseTime = LocalDateTime.parse(strPrxyRespTime, dtf);

		proxyMessage.setRequestDt(prxyRequestTime);
		proxyMessage.setResponseDt(prxyResponseTime);
		
		proxyMessage.setScndIdType(regRequest.getRegn().getPrxyRegn().getScndId().getTp());
		proxyMessage.setScndValue(regRequest.getRegn().getPrxyRegn().getScndId().getVal());
		proxyMessage.setTownName(regRequest.getSplmtryData().get(0).getEnvlp().getCstmr().getTwnNm());

		proxyMessage.setOperationType(regRequest.getRegn().getRegnTp().value());
		
		String errorStatus = exchange.getMessage().getHeader("hdr_error_status", String.class);
		String errorMesg = exchange.getMessage().getHeader("hdr_error_mesg", String.class);

		BusinessMessage biResponse = exchange.getMessage().getHeader("prx_biresponse", BusinessMessage.class);
		
		if (!(null == biResponse))
			proxyMessage.setRespStatus(biResponse.getDocument().getPrxyRegnRspn().getRegnRspn().getPrxRspnSts().value());

		
		if (!(null == errorStatus)) {
			proxyMessage.setCallStatus(errorStatus);
			if (!(null == errorMesg)) 
				proxyMessage.setErrorMessage(errorMesg);
		} 
		else
			proxyMessage.setCallStatus("SUCCESS");

		
		proxyMessageRepo.save(proxyMessage);
		
	}

}
