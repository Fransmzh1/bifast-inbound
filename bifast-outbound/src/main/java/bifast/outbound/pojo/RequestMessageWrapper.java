package bifast.outbound.pojo;

import java.time.Instant;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;

import bifast.library.iso20022.custom.BusinessMessage;
import bifast.outbound.corebank.pojo.CBDebitInstructionRequestPojo;
import bifast.outbound.pojo.chnlrequest.ChnlAccountEnquiryRequestPojo;
import bifast.outbound.pojo.chnlrequest.ChnlCreditTransferRequestPojo;
import bifast.outbound.pojo.chnlrequest.ChnlPaymentStatusRequestPojo;
import bifast.outbound.pojo.chnlrequest.ChnlProxyRegistrationRequestPojo;
import bifast.outbound.pojo.chnlrequest.ChnlProxyResolutionRequestPojo;
import bifast.outbound.pojo.chnlrequest.PaymentStatusRequestSAFPojo;

public class RequestMessageWrapper {

	private String requestId;  // no internal reference dari channel
	private String msgName;
	private String channelId;   // nomor id channel = clientId
	private String channelType;
	private String merchantType;
//	private Long chnlTrnsIdTable;
	private String senderAccountNumber;
	private Instant komiStart;
	private Instant cihubStart;
	private String cihubEncriptedRequest;
	private String cihubEncriptedResponse;
	
	private String komiTrxId;   // internal tranaction id dari komi
								// digenerate dari utilService.getkomiid;
//	private LocalDateTime requestTime;

	@JsonProperty("DebitRequest")
	private CBDebitInstructionRequestPojo debitRequest;
	
	@JsonProperty("AccountEnquiryRequest")
	private ChnlAccountEnquiryRequestPojo chnlAccountEnquiryRequest;
	
	@JsonProperty("CreditTransferRequest")
	private ChnlCreditTransferRequestPojo chnlCreditTransferRequest;

	@JsonProperty("PaymentStatusRequest")
	private ChnlPaymentStatusRequestPojo chnlPaymentStatusRequest;

	private PaymentStatusRequestSAFPojo PaymentStatusRequestSAF;

	@JsonProperty("ProxyRegistrationRequest")
	private ChnlProxyRegistrationRequestPojo chnlProxyRegistrationRequest;

	@JsonProperty("ProxyResolutionRequest")
	private ChnlProxyResolutionRequestPojo chnlProxyResolutionRequest;

	private BusinessMessage accountEnquiryRequest;
	private BusinessMessage creditTransferRequest;
	private BusinessMessage proxyRegistrationRequest;
	private BusinessMessage proxyResolutionRequest;
	
	public String getRequestId() {
		return requestId;
	}
	public void setRequestId(String requestId) {
		this.requestId = requestId;
	}
	public String getMsgName() {
		return msgName;
	}
	public void setMsgName(String msgName) {
		this.msgName = msgName;
	}
	public String getChannelId() {
		return channelId;
	}
	public void setChannelId(String channelId) {
		this.channelId = channelId;
	}
	public String getChannelType() {
		return channelType;
	}
	public void setChannelType(String channelType) {
		this.channelType = channelType;
	}
	public String getSenderAccountNumber() {
		return senderAccountNumber;
	}
	public void setSenderAccountNumber(String senderAccountNumber) {
		this.senderAccountNumber = senderAccountNumber;
	}
	public String getMerchantType() {
		return merchantType;
	}
	public void setMerchantType(String merchantType) {
		this.merchantType = merchantType;
	}
	public Instant getKomiStart() {
		return komiStart;
	}
	public void setKomiStart(Instant komiStart) {
		this.komiStart = komiStart;
	}
	public String getKomiTrxId() {
		return komiTrxId;
	}
	public Instant getCihubStart() {
		return cihubStart;
	}
	public void setCihubStart(Instant cihubStart) {
		this.cihubStart = cihubStart;
	}
	public void setKomiTrxId(String komiTrxId) {
		this.komiTrxId = komiTrxId;
	}
//	public LocalDateTime getRequestTime() {
//		return requestTime;
//	}
//	public void setRequestTime(LocalDateTime requestTime) {
//		this.requestTime = requestTime;
//	}
	public CBDebitInstructionRequestPojo getDebitRequest() {
		return debitRequest;
	}
	public void setDebitRequest(CBDebitInstructionRequestPojo debitRequest) {
		this.debitRequest = debitRequest;
	}
	public ChnlAccountEnquiryRequestPojo getChnlAccountEnquiryRequest() {
		return chnlAccountEnquiryRequest;
	}
	public void setChnlAccountEnquiryRequest(ChnlAccountEnquiryRequestPojo chnlAccountEnquiryRequest) {
		this.chnlAccountEnquiryRequest = chnlAccountEnquiryRequest;
	}
	public ChnlCreditTransferRequestPojo getChnlCreditTransferRequest() {
		return chnlCreditTransferRequest;
	}
	public void setChnlCreditTransferRequest(ChnlCreditTransferRequestPojo chnlCreditTransferRequest) {
		this.chnlCreditTransferRequest = chnlCreditTransferRequest;
	}
	public ChnlPaymentStatusRequestPojo getChnlPaymentStatusRequest() {
		return chnlPaymentStatusRequest;
	}
	public void setChnlPaymentStatusRequest(ChnlPaymentStatusRequestPojo chnlPaymentStatusRequest) {
		this.chnlPaymentStatusRequest = chnlPaymentStatusRequest;
	}
	public ChnlProxyRegistrationRequestPojo getChnlProxyRegistrationRequest() {
		return chnlProxyRegistrationRequest;
	}
	public void setChnlProxyRegistrationRequest(ChnlProxyRegistrationRequestPojo chnlProxyRegistrationRequest) {
		this.chnlProxyRegistrationRequest = chnlProxyRegistrationRequest;
	}
	public ChnlProxyResolutionRequestPojo getChnlProxyResolutionRequest() {
		return chnlProxyResolutionRequest;
	}
	public void setChnlProxyResolutionRequest(ChnlProxyResolutionRequestPojo chnlProxyResolutionRequest) {
		this.chnlProxyResolutionRequest = chnlProxyResolutionRequest;
	}
	public BusinessMessage getAccountEnquiryRequest() {
		return accountEnquiryRequest;
	}
	public void setAccountEnquiryRequest(BusinessMessage accountEnquiryRequest) {
		this.accountEnquiryRequest = accountEnquiryRequest;
	}
	public PaymentStatusRequestSAFPojo getPaymentStatusRequestSAF() {
		return PaymentStatusRequestSAF;
	}
	public void setPaymentStatusRequestSAF(PaymentStatusRequestSAFPojo paymentStatusRequestSAF) {
		PaymentStatusRequestSAF = paymentStatusRequestSAF;
	}
	public BusinessMessage getCreditTransferRequest() {
		return creditTransferRequest;
	}
	public void setCreditTransferRequest(BusinessMessage creditTransferRequest) {
		this.creditTransferRequest = creditTransferRequest;
	}
	public BusinessMessage getProxyRegistrationRequest() {
		return proxyRegistrationRequest;
	}
	public void setProxyRegistrationRequest(BusinessMessage proxyRegistrationRequest) {
		this.proxyRegistrationRequest = proxyRegistrationRequest;
	}
	public BusinessMessage getProxyResolutionRequest() {
		return proxyResolutionRequest;
	}
	public void setProxyResolutionRequest(BusinessMessage proxyResolutionRequest) {
		this.proxyResolutionRequest = proxyResolutionRequest;
	}
	public String getCihubEncriptedRequest() {
		return cihubEncriptedRequest;
	}
	public void setCihubEncriptedRequest(String cihubEncriptedRequest) {
		this.cihubEncriptedRequest = cihubEncriptedRequest;
	}
	public String getCihubEncriptedResponse() {
		return cihubEncriptedResponse;
	}
	public void setCihubEncriptedResponse(String cihubEncriptedResponse) {
		this.cihubEncriptedResponse = cihubEncriptedResponse;
	}
	



}
