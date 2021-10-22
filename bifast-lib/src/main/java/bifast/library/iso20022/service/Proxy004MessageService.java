package bifast.library.iso20022.service;

import java.util.GregorianCalendar;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bifast.library.config.LibConfig;
import bifast.library.iso20022.custom.BusinessMessage;
import bifast.library.iso20022.prxy004.AccountIdentification4Choice;
import bifast.library.iso20022.prxy004.CashAccount40;
import bifast.library.iso20022.prxy004.CashAccountType2ChoiceProxy;
import bifast.library.iso20022.prxy004.GenericAccountIdentification1;
import bifast.library.iso20022.prxy004.ProxyDefinition1;
import bifast.library.iso20022.prxy004.ProxyLookUpAccount1;
import bifast.library.iso20022.prxy004.ProxyLookUpRegistration1;
import bifast.library.iso20022.prxy004.ProxyLookUpResponse1;
import bifast.library.iso20022.prxy004.ProxyLookUpResponseV01;
import bifast.library.iso20022.prxy004.AccountIdentification4Choice;
import bifast.library.iso20022.prxy004.BIAddtlCstmrInf;
import bifast.library.iso20022.prxy004.BISupplementaryData1;
import bifast.library.iso20022.prxy004.BISupplementaryDataEnvelope1;
import bifast.library.iso20022.prxy004.BranchAndFinancialInstitutionIdentification5;
import bifast.library.iso20022.prxy004.CashAccount40;
import bifast.library.iso20022.prxy004.CashAccountType2ChoiceProxy;
import bifast.library.iso20022.prxy004.FinancialInstitutionIdentification8;
import bifast.library.iso20022.prxy004.GenericAccountIdentification1;
import bifast.library.iso20022.prxy004.GenericFinancialIdentification1;
import bifast.library.iso20022.prxy004.GroupHeader60;
import bifast.library.iso20022.prxy004.OriginalGroupInformation3;
import bifast.library.iso20022.prxy004.Party12Choice;
import bifast.library.iso20022.prxy004.ProxyDefinition1;
import bifast.library.iso20022.prxy004.ProxyLookUpAccount1;
import bifast.library.iso20022.prxy004.ProxyLookUpRegistration1;
import bifast.library.iso20022.prxy004.ProxyLookUpResponse1;
import bifast.library.iso20022.prxy004.ProxyLookUpResponseV01;
import bifast.library.iso20022.prxy004.ProxyStatusChoice;
import bifast.library.iso20022.prxy004.ProxyStatusCode;


@Service
public class Proxy004MessageService {
	
	@Autowired
	private LibConfig config;
	
	public ProxyLookUpResponseV01 proxyResolutionResponse (Proxy004Seed seed, 
			BusinessMessage orgnlMessage) 
			throws DatatypeConfigurationException {
		
		
		//Response
		ProxyLookUpResponseV01 response = new ProxyLookUpResponseV01();
		
		GroupHeader60 grpHdr = new GroupHeader60();
		grpHdr.setMsgId(seed.getMsgId());
		
		GregorianCalendar gcal = new GregorianCalendar();
		XMLGregorianCalendar xcal = DatatypeFactory.newInstance().newXMLGregorianCalendar(gcal);
		grpHdr.setCreDtTm(xcal);
		
		// GrpHdr / MsgSndr / Agt/ FinInstnId/ Othr /Id
		GenericFinancialIdentification1 sndrId = new GenericFinancialIdentification1();
		sndrId.setId(config.getBankcode());
		FinancialInstitutionIdentification8 finInstnId = new FinancialInstitutionIdentification8();
		finInstnId.setOthr(sndrId);
		BranchAndFinancialInstitutionIdentification5 sndrAgt = new BranchAndFinancialInstitutionIdentification5();
		sndrAgt.setFinInstnId(finInstnId);
		
		Party12Choice msgSndr = new Party12Choice();
		msgSndr.setAgt(sndrAgt);
		
		grpHdr.setMsgRcpt(msgSndr);
		response.setGrpHdr(grpHdr);	
		
		
		ProxyLookUpResponse1 regnRspn = new ProxyLookUpResponse1();
				  
		regnRspn.setOrgnlId(seed.getMsgId());
		  
		 ProxyDefinition1 prxyRtrvl = new ProxyDefinition1();
		 prxyRtrvl.setTp(seed.getPrxyRtrvlTp()); 
		 prxyRtrvl.setVal(seed.getPrxyRtrvlVal());
		 
		 regnRspn.setOrgnlPrxyRtrvl(prxyRtrvl);
		 
		 ProxyLookUpRegistration1 regn = new ProxyLookUpRegistration1();
		 
			if(seed.getStatus().equals("ACTC")) {
				regn.setPrxRspnSts(ProxyStatusCode.ACTC);
			}else {
				regn.setPrxRspnSts(ProxyStatusCode.RJCT);
			}
				
			ProxyStatusChoice StsRsnInf = new ProxyStatusChoice();
			StsRsnInf.setPrtry(seed.getReason());
			regn.setStsRsnInf(StsRsnInf);
			
			regn.setPrxy(prxyRtrvl);
			
			if(seed.getRegisterBank() != null) {
			
				ProxyLookUpAccount1 prxyAcc = new ProxyLookUpAccount1();
				GenericFinancialIdentification1 othrRegnRspn = new GenericFinancialIdentification1();
				othrRegnRspn.setId(seed.getRegisterBank());
				
				FinancialInstitutionIdentification8 finInstnIdRegnRspn = new FinancialInstitutionIdentification8();
				finInstnIdRegnRspn.setOthr(othrRegnRspn);
				
				BranchAndFinancialInstitutionIdentification5 agtRegnRspn = new BranchAndFinancialInstitutionIdentification5();
				agtRegnRspn.setFinInstnId(finInstnIdRegnRspn);
				prxyAcc.setAgt(agtRegnRspn);
				
				GenericAccountIdentification1 othr = new GenericAccountIdentification1();
				othr.setId(seed.getAccountNumber());
				
				AccountIdentification4Choice id = new AccountIdentification4Choice();
				id.setOthr(othr);
				
				CashAccountType2ChoiceProxy tp = new CashAccountType2ChoiceProxy();
				tp.setPrtry(seed.getAccountType());
				
				CashAccount40 cashAccount40 =  new CashAccount40();
				cashAccount40.setId(id);
				
				cashAccount40.setTp(tp);
				cashAccount40.setNm(seed.getAccountName());
				
				prxyAcc.setAcct(cashAccount40);
				prxyAcc.setDsplNm(seed.getDisplayName());
				prxyAcc.setRegnId("0102030405060708");
				
				regn.setRegn(prxyAcc);
				
				
			 // Lookup / PrxyOnly ProxyLookUpChoice1 lookup = new ProxyLookUpChoice1();
				
			}	 
		regnRspn.setRegnRspn(regn);
		response.setLkUpRspn(regnRspn);
		
		///
		BIAddtlCstmrInf bIAddtlCstmrInf = new BIAddtlCstmrInf();
		bIAddtlCstmrInf.setId("5302022290990001");
		bIAddtlCstmrInf.setTp("01");
		bIAddtlCstmrInf.setTwnNm("0300");
		
		///
		BISupplementaryDataEnvelope1 biSupplementaryDataEnvelope1 =  new BISupplementaryDataEnvelope1();
		biSupplementaryDataEnvelope1.setCstmr(bIAddtlCstmrInf);
		
		///
		BISupplementaryData1 biSupplementaryData1 = new BISupplementaryData1();
		biSupplementaryData1.setEnvlp(biSupplementaryDataEnvelope1);
		
		///
		response.getSplmtryData().add(biSupplementaryData1);
		
		return response;
	}

}
