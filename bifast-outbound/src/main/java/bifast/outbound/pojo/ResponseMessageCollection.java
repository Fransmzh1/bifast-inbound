package bifast.outbound.pojo;

import bifast.library.iso20022.custom.BusinessMessage;
import bifast.outbound.pojo.chnlresponse.ChnlAccountEnquiryResponsePojo;
import bifast.outbound.pojo.chnlresponse.ChnlCreditTransferResponsePojo;
import bifast.outbound.pojo.chnlresponse.ChnlProxyRegistrationResponsePojo;
import bifast.outbound.pojo.chnlresponse.ChnlProxyResolutionResponsePojo;

public class ResponseMessageCollection {

	private String callStatus;
	private String responseCode;
	private String lastError;
	
	private ChnlFailureResponsePojo fault;
	private ChnlAccountEnquiryResponsePojo chnlAccountEnquiryResponse;
	private ChnlCreditTransferResponsePojo chnlCreditTransferResponse;
	private ChnlProxyRegistrationResponsePojo chnlProxyRegistrationResponse;
	private ChnlProxyResolutionResponsePojo chnlProxyResolutionResponse;
	
	private BusinessMessage accountEnquiryResponse;
	private BusinessMessage creditTransferResponse;
	private BusinessMessage proxyRegistrationResponse;
	private BusinessMessage proxyResolutionResponse;
	
	public String getCallStatus() {
		return callStatus;
	}
	public void setCallStatus(String callStatus) {
		this.callStatus = callStatus;
	}
	public String getResponseCode() {
		return responseCode;
	}
	public void setResponseCode(String responseCode) {
		this.responseCode = responseCode;
	}
	public String getLastError() {
		return lastError;
	}
	public void setLastError(String lastError) {
		this.lastError = lastError;
	}
	public ChnlFailureResponsePojo getFault() {
		return fault;
	}
	public void setFault(ChnlFailureResponsePojo fault) {
		this.fault = fault;
	}
	public ChnlAccountEnquiryResponsePojo getChnlAccountEnquiryResponse() {
		return chnlAccountEnquiryResponse;
	}
	public void setChnlAccountEnquiryResponse(ChnlAccountEnquiryResponsePojo chnlAccountEnquiryResponse) {
		this.chnlAccountEnquiryResponse = chnlAccountEnquiryResponse;
	}
	public ChnlCreditTransferResponsePojo getChnlCreditTransferResponse() {
		return chnlCreditTransferResponse;
	}
	public void setChnlCreditTransferResponse(ChnlCreditTransferResponsePojo chnlCreditTransferResponse) {
		this.chnlCreditTransferResponse = chnlCreditTransferResponse;
	}
	public ChnlProxyRegistrationResponsePojo getChnlProxyRegistrationResponse() {
		return chnlProxyRegistrationResponse;
	}
	public void setChnlProxyRegistrationResponse(ChnlProxyRegistrationResponsePojo chnlProxyRegistrationResponse) {
		this.chnlProxyRegistrationResponse = chnlProxyRegistrationResponse;
	}
	public ChnlProxyResolutionResponsePojo getChnlProxyResolutionResponse() {
		return chnlProxyResolutionResponse;
	}
	public void setChnlProxyResolutionResponse(ChnlProxyResolutionResponsePojo chnlProxyResolutionResponse) {
		this.chnlProxyResolutionResponse = chnlProxyResolutionResponse;
	}
	public BusinessMessage getAccountEnquiryResponse() {
		return accountEnquiryResponse;
	}
	public void setAccountEnquiryResponse(BusinessMessage accountEnquiryResponse) {
		this.accountEnquiryResponse = accountEnquiryResponse;
	}
	public BusinessMessage getCreditTransferResponse() {
		return creditTransferResponse;
	}
	public void setCreditTransferResponse(BusinessMessage creditTransferResponse) {
		this.creditTransferResponse = creditTransferResponse;
	}
	public BusinessMessage getProxyRegistrationResponse() {
		return proxyRegistrationResponse;
	}
	public void setProxyRegistrationResponse(BusinessMessage proxyRegistrationResponse) {
		this.proxyRegistrationResponse = proxyRegistrationResponse;
	}
	public BusinessMessage getProxyResolutionResponse() {
		return proxyResolutionResponse;
	}
	public void setProxyResolutionResponse(BusinessMessage proxyResolutionResponse) {
		this.proxyResolutionResponse = proxyResolutionResponse;
	}
	

	

}
