package bifast.outbound.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class AccountEnquiry {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	private Long logMessageId;
	
	@Column(length=20)
	private String intrRefId;
	
	@Column(name="ORIGN_BANK", length=20)
	private String originatingBank;
	@Column(name="RECPT_BANK", length=20)
	private String recipientBank;

	private BigDecimal amount;
	
	@Column(length=50)
	private String accountNo;
	private LocalDateTime creDt;

	@Column(length=20)
	private String status;

	
	public AccountEnquiry () {}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getLogMessageId() {
		return logMessageId;
	}

	public void setLogMessageId(Long logMessageId) {
		this.logMessageId = logMessageId;
	}

	public String getIntrRefId() {
		return intrRefId;
	}

	public void setIntrRefId(String intrRefId) {
		this.intrRefId = intrRefId;
	}

	public String getOriginatingBank() {
		return originatingBank;
	}

	public void setOriginatingBank(String originatingBank) {
		this.originatingBank = originatingBank;
	}

	public String getRecipientBank() {
		return recipientBank;
	}

	public void setRecipientBank(String recipientBank) {
		this.recipientBank = recipientBank;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public String getAccountNo() {
		return accountNo;
	}

	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}

	public LocalDateTime getCreDt() {
		return creDt;
	}

	public void setCreDt(LocalDateTime creDt) {
		this.creDt = creDt;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}


	
	
}
