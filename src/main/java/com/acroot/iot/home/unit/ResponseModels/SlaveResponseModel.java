package com.acroot.iot.home.unit.ResponseModels;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SlaveResponseModel {

	@JsonProperty
	Long slaveId;
	
	@JsonProperty
	String slaveUid;
	
	@JsonProperty
	String secret;
	
	@JsonProperty
	String ipAddress;
	
	@JsonProperty
	boolean health;
	
	@JsonProperty
	String errorMessage;

	public Long getSlaveId() {
		return slaveId;
	}

	public void setSlaveId(Long slaveId) {
		this.slaveId = slaveId;
	}

	public String getSlaveUid() {
		return slaveUid;
	}

	public void setSlaveUid(String slaveUid) {
		this.slaveUid = slaveUid;
	}

	public String getSecret() {
		return secret;
	}

	public void setSecret(String secret) {
		this.secret = secret;
	}

	public String getIpAddress() {
		return ipAddress;
	}

	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

	public boolean isHealth() {
		return health;
	}

	public void setHealth(boolean health) {
		this.health = health;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	
}
