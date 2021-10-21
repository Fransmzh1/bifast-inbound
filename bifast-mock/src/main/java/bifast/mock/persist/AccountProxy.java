package bifast.mock.persist;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class AccountProxy {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String registerBank;
    private String accountNumber;
    private String proxyType;
    private String proxyVal;
    private String accountType;
    private String accountName;
    private String displayName;
    private String accountStatus;
    private String scndIdTp;
    private String scndIdVal;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getRegisterBank() {
		return registerBank;
	}
	public void setRegisterBank(String registerBank) {
		this.registerBank = registerBank;
	}
	public String getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}
	public String getProxyType() {
		return proxyType;
	}
	public void setProxyType(String proxyType) {
		this.proxyType = proxyType;
	}
	public String getProxyVal() {
		return proxyVal;
	}
	public void setProxyVal(String proxyVal) {
		this.proxyVal = proxyVal;
	}
	public String getAccountType() {
		return accountType;
	}
	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}
	public String getAccountName() {
		return accountName;
	}
	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}
	public String getDisplayName() {
		return displayName;
	}
	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}
	public String getAccountStatus() {
		return accountStatus;
	}
	public void setAccountStatus(String accountStatus) {
		this.accountStatus = accountStatus;
	}
	public String getScndIdTp() {
		return scndIdTp;
	}
	public void setScndIdTp(String scndIdTp) {
		this.scndIdTp = scndIdTp;
	}
	public String getScndIdVal() {
		return scndIdVal;
	}
	public void setScndIdVal(String scndIdVal) {
		this.scndIdVal = scndIdVal;
	}
    
	
}
