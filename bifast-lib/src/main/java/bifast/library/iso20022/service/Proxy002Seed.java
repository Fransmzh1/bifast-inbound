package bifast.library.iso20022.service;

import java.math.BigDecimal;

import javax.xml.bind.annotation.XmlElement;

import bifast.library.iso20022.prxy001.BranchAndFinancialInstitutionIdentification5;
import bifast.library.iso20022.prxy001.CashAccount40;
import bifast.library.iso20022.prxy001.Party30Choice;
import bifast.library.iso20022.prxy001.ScndIdDefinition1;

public class Proxy002Seed {
	
	private String msgId; 
	private String creDtTm;
	
	private String msgRcptAgtId;
	
    protected String prtry;
    protected String regnId;
    
    protected String agtId;
    
    private String status; 
	private String reason;
	private String additionalInfo;
	
    protected String cstmrId;
    protected String cstmrTp;
    protected String cstmrRsdntSts;
    protected String cstmrTwnNm;
	public String getMsgId() {
		return msgId;
	}
	public void setMsgId(String msgId) {
		this.msgId = msgId;
	}
	public String getCreDtTm() {
		return creDtTm;
	}
	public void setCreDtTm(String creDtTm) {
		this.creDtTm = creDtTm;
	}
	public String getMsgRcptAgtId() {
		return msgRcptAgtId;
	}
	public void setMsgRcptAgtId(String msgRcptAgtId) {
		this.msgRcptAgtId = msgRcptAgtId;
	}
	public String getPrtry() {
		return prtry;
	}
	public void setPrtry(String prtry) {
		this.prtry = prtry;
	}
	public String getRegnId() {
		return regnId;
	}
	public void setRegnId(String regnId) {
		this.regnId = regnId;
	}
	public String getAgtId() {
		return agtId;
	}
	public void setAgtId(String agtId) {
		this.agtId = agtId;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public String getAdditionalInfo() {
		return additionalInfo;
	}
	public void setAdditionalInfo(String additionalInfo) {
		this.additionalInfo = additionalInfo;
	}
	public String getCstmrId() {
		return cstmrId;
	}
	public void setCstmrId(String cstmrId) {
		this.cstmrId = cstmrId;
	}
	public String getCstmrTp() {
		return cstmrTp;
	}
	public void setCstmrTp(String cstmrTp) {
		this.cstmrTp = cstmrTp;
	}
	public String getCstmrRsdntSts() {
		return cstmrRsdntSts;
	}
	public void setCstmrRsdntSts(String cstmrRsdntSts) {
		this.cstmrRsdntSts = cstmrRsdntSts;
	}
	public String getCstmrTwnNm() {
		return cstmrTwnNm;
	}
	public void setCstmrTwnNm(String cstmrTwnNm) {
		this.cstmrTwnNm = cstmrTwnNm;
	}
	
    
}
