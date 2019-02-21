package com.acroot.iot.home.unit.ResponseModels;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SwitchResponseModel {
		
	@JsonProperty
	Long switchId;
	
	@JsonProperty
	String switchUid;
	
	@JsonProperty
	boolean status;
	
	@JsonProperty
	String errorMessage;
	
	@JsonProperty
	String tag;
	
	
	

	

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

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}


	
	
	
}

