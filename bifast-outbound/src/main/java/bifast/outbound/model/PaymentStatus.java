package bifast.outbound.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

@Entity(name="KC_PAYMENT_STATUS")
public class PaymentStatus {

	@Id 
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_generator")
	@SequenceGenerator(name="seq_generator", sequenceName = "table_seq_generator", allocationSize=1)
	private Long id;
	
	private String internRefId;
	
	private Long chnlTrxId;

	@Column(length=5000)
	private String requestFullMessage;
	@Column(length=5000)
	private String responseFullMessage;
	
	@Column(name="BIZMSGID", length=50)
	private String BizMsgIdr;
	
	@Column(name="ORGN_ENDTOENDID", length=50)
	private String orgnlEndToEndId;
	
	private String status;
	private String errorMsg;

	private LocalDateTime requestDt;
	private Long cihubElapsedTime;

	private LocalDateTime lastUpdateDt;

	@Column(length=16)
	private String komiTrnsId;

	private String saf;
	private int retryCount;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getInternRefId() {
		return internRefId;
	}
	public void setInternRefId(String internRefId) {
		this.internRefId = internRefId;
	}
	public Long getChnlTrxId() {
		return chnlTrxId;
	}
	public void setChnlTrxId(Long chnlTrxId) {
		this.chnlTrxId = chnlTrxId;
	}
	public String getRequestFullMessage() {
		return requestFullMessage;
	}
	public void setRequestFullMessage(String requestFullMessage) {
		this.requestFullMessage = requestFullMessage;
	}
	public String getResponseFullMessage() {
		return responseFullMessage;
	}
	public void setResponseFullMessage(String responseFullMessage) {
		this.responseFullMessage = responseFullMessage;
	}
	public String getBizMsgIdr() {
		return BizMsgIdr;
	}
	public void setBizMsgIdr(String bizMsgIdr) {
		BizMsgIdr = bizMsgIdr;
	}
	public String getOrgnlEndToEndId() {
		return orgnlEndToEndId;
	}
	public void setOrgnlEndToEndId(String orgnlEndToEndId) {
		this.orgnlEndToEndId = orgnlEndToEndId;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public LocalDateTime getLastUpdateDt() {
		return lastUpdateDt;
	}
	public void setLastUpdateDt(LocalDateTime lastUpdateDt) {
		this.lastUpdateDt = lastUpdateDt;
	}
	public String getKomiTrnsId() {
		return komiTrnsId;
	}
	public void setKomiTrnsId(String komiTrnsId) {
		this.komiTrnsId = komiTrnsId;
	}
	public String getErrorMsg() {
		return errorMsg;
	}
	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}
	public LocalDateTime getRequestDt() {
		return requestDt;
	}
	public void setRequestDt(LocalDateTime requestDt) {
		this.requestDt = requestDt;
	}
	public Long getCihubElapsedTime() {
		return cihubElapsedTime;
	}
	public void setCihubElapsedTime(Long cihubElapsedTime) {
		this.cihubElapsedTime = cihubElapsedTime;
	}
	public String getSaf() {
		return saf;
	}
	public void setSaf(String saf) {
		this.saf = saf;
	}
	public int getRetryCount() {
		return retryCount;
	}
	public void setRetryCount(int retryCount) {
		this.retryCount = retryCount;
	}

	
}
