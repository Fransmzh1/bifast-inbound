package bifast.outbound.corebank.pojo;

public class CBDebitResponsePojo {

	private String transactionId;
	private String dateTime;
	private String merchantType;
	private String terminalId;
	private String noRef;
	private String status;
	private String reason;
	private String additionalInfo;
	private String accountNumber;
	
	public String getTransactionId() {
		return transactionId;
	}
	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}
	public String getDateTime() {
		return dateTime;
	}
	public void setDateTime(String dateTime) {
		this.dateTime = dateTime;
	}
	public String getMerchantType() {
		return merchantType;
	}
	public void setMerchantType(String merchantType) {
		this.merchantType = merchantType;
	}
	public String getTerminalId() {
		return terminalId;
	}
	public void setTerminalId(String terminalId) {
		this.terminalId = terminalId;
	}
	public String getNoRef() {
		return noRef;
	}
	public void setNoRef(String noRef) {
		this.noRef = noRef;
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
	public String getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	
	
}
