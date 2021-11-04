package bifast.inbound.pojo;

import java.time.Instant;
import java.time.LocalDateTime;

import bifast.library.iso20022.custom.BusinessMessage;

public class ProcessDataPojo {

	private Long komiCounter;
	private String komiTrnsId;
	private String inbMesgType;
	private LocalDateTime receivedDt;
	private Instant startTime;
	
	private String textDataReceived;
	
	private BusinessMessage biRequestMsg;
	private BusinessMessage biResponseMsg;

	public Long getKomiCounter() {
		return komiCounter;
	}

	public void setKomiCounter(Long komiCounter) {
		this.komiCounter = komiCounter;
	}

	public String getKomiTrnsId() {
		return komiTrnsId;
	}

	public void setKomiTrnsId(String komiTrnsId) {
		this.komiTrnsId = komiTrnsId;
	}

	public String getInbMesgType() {
		return inbMesgType;
	}

	public void setInbMesgType(String inbMesgType) {
		this.inbMesgType = inbMesgType;
	}

	public LocalDateTime getReceivedDt() {
		return receivedDt;
	}

	public void setReceivedDt(LocalDateTime receivedDt) {
		this.receivedDt = receivedDt;
	}

	public Instant getStartTime() {
		return startTime;
	}

	public void setStartTime(Instant startTime) {
		this.startTime = startTime;
	}

	public String getTextDataReceived() {
		return textDataReceived;
	}

	public void setTextDataReceived(String textDataReceived) {
		this.textDataReceived = textDataReceived;
	}

	public BusinessMessage getBiRequestMsg() {
		return biRequestMsg;
	}

	public void setBiRequestMsg(BusinessMessage biRequestMsg) {
		this.biRequestMsg = biRequestMsg;
	}

	public BusinessMessage getBiResponseMsg() {
		return biResponseMsg;
	}

	public void setBiResponseMsg(BusinessMessage biResponseMsg) {
		this.biResponseMsg = biResponseMsg;
	}

	
	
}
