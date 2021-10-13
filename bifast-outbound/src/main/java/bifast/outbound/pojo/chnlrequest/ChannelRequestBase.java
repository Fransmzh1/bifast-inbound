package bifast.outbound.pojo.chnlrequest;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ChannelRequestBase {

	@JsonProperty("NoRef")
	private String intrnRefId;
	@JsonProperty("Channel")
	private String channel; 
	@JsonProperty("CategoryPurpose")
	private String categoryPurpose;

	@JsonProperty("RecipientBank")
	private String recptBank; 

	public String getIntrnRefId() {
		return intrnRefId;
	}
	public void setIntrnRefId(String intrnRefId) {
		this.intrnRefId = intrnRefId;
	}
	public String getChannel() {
		return channel;
	}
	public void setChannel(String channel) {
		this.channel = channel;
	}
	public String getCategoryPurpose() {
		return categoryPurpose;
	}
	public void setCategoryPurpose(String categoryPurpose) {
		this.categoryPurpose = categoryPurpose;
	}
	public String getRecptBank() {
		return recptBank;
	}
	public void setRecptBank(String recptBank) {
		this.recptBank = recptBank;
	} 



}
