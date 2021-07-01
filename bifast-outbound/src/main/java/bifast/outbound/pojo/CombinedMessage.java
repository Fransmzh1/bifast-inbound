package bifast.outbound.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonRootName;

import bifast.library.iso20022.custom.BusinessMessage;
import bifast.outbound.accountenquiry.ChannelAccountEnquiryReq;
import bifast.outbound.credittransfer.ChannelCreditTransferRequest;
import bifast.outbound.ficredittransfer.ChannelFICreditTransferReq;
import bifast.outbound.paymentstatus.ChannelPaymentStatusRequest;
import bifast.outbound.reversect.ChannelReverseCreditTransferRequest;

@JsonRootName("CombinedMessages")
@JsonPropertyOrder({"accountEnquiryRequest", 
	"creditTransferRequest",
	"fiCreditTransferRequest", 
	"paymentStatusRequest", 
	"reverseCreditTransferRequest",
	"outboundMessage",
	"responseMessage"})
public class CombinedMessage {

	@JsonProperty("AccountEnquiryRequest")
	private ChannelAccountEnquiryReq accountEnquiryRequest;
	@JsonProperty("CreditTransferRequest")
	private ChannelCreditTransferRequest creditTransferRequest;
	@JsonProperty("FICreditTransferRequest")
	private ChannelFICreditTransferReq fiCreditTransferRequest;
	@JsonProperty("PaymentStatusRequest")
	private ChannelPaymentStatusRequest paymentStatusRequest;
	@JsonProperty("ReverseCreditTransferRequest")
	private ChannelReverseCreditTransferRequest reverseCreditTransferRequest;
	
	@JsonProperty("OutboundMessage")
	private BusinessMessage outboundMessage;
	@JsonProperty("ResponseMessage")
	private BusinessMessage responseMessage;

	public ChannelAccountEnquiryReq getAccountEnquiryRequest() {
		return accountEnquiryRequest;
	}
	public void setAccountEnquiryRequest(ChannelAccountEnquiryReq accountEnquiryRequest) {
		this.accountEnquiryRequest = accountEnquiryRequest;
	}
	public ChannelCreditTransferRequest getCreditTransferRequest() {
		return creditTransferRequest;
	}
	public void setCreditTransferRequest(ChannelCreditTransferRequest creditTransferRequest) {
		this.creditTransferRequest = creditTransferRequest;
	}
	public ChannelFICreditTransferReq getFiCreditTransferRequest() {
		return fiCreditTransferRequest;
	}
	public void setFiCreditTransferRequest(ChannelFICreditTransferReq fiCreditTransferRequest) {
		this.fiCreditTransferRequest = fiCreditTransferRequest;
	}
	public ChannelPaymentStatusRequest getPaymentStatusRequest() {
		return paymentStatusRequest;
	}
	public void setPaymentStatusRequest(ChannelPaymentStatusRequest paymentStatusRequest) {
		this.paymentStatusRequest = paymentStatusRequest;
	}
	public ChannelReverseCreditTransferRequest getReverseCreditTransferRequest() {
		return reverseCreditTransferRequest;
	}
	public void setReverseCreditTransferRequest(ChannelReverseCreditTransferRequest reverseCreditTransferRequest) {
		this.reverseCreditTransferRequest = reverseCreditTransferRequest;
	}
	public BusinessMessage getOutboundMessage() {
		return outboundMessage;
	}
	public void setOutboundMessage(BusinessMessage outboundMessage) {
		this.outboundMessage = outboundMessage;
	}
	public BusinessMessage getResponseMessage() {
		return responseMessage;
	}
	public void setResponseMessage(BusinessMessage responseMessage) {
		this.responseMessage = responseMessage;
	}

	
	
}
