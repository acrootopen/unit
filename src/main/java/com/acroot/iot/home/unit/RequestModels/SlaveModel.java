package com.acroot.iot.home.unit.RequestModels;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SlaveModel {
		
	@JsonProperty
	String slaveUID;
	
	@JsonProperty
	String secretKey;
	
	@JsonProperty
	String ipAddress;
	
	@JsonProperty
	boolean health;

	public String getSlaveUID() {
		return slaveUID;
	}

	public void setSlaveUID(String slaveUID) {
		this.slaveUID = slaveUID;
	}

	public String getSecretKey() {
		return secretKey;
	}

	public void setSecretKey(String secretKey) {
		this.secretKey = secretKey;
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
	
	
	

}
