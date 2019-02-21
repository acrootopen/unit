package com.acroot.iot.home.unit.ResponseModels;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RemoteSwitchesResponseModel {
	
	@JsonProperty
	Long switchId;
	
	@JsonProperty
	String switchUid;
	
	@JsonProperty
	Long slaveId;
	
	@JsonProperty
	boolean status;
	
	@JsonProperty
	String tag;
	
	@JsonProperty
	String errorMessage;

	public Long getSwitchId() {
		return switchId;
	}

	public void setSwitchId(Long switchId) {
		this.switchId = switchId;
	}

	public String getSwitchUid() {
		return switchUid;
	}

	public void setSwitchUid(String switchUid) {
		this.switchUid = switchUid;
	}

	public Long getSlaveId() {
		return slaveId;
	}

	public void setSlaveId(Long slaveId) {
		this.slaveId = slaveId;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

}
