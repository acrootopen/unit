package com.acroot.iot.home.unit.RequestModels;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RemoteSwitchesModel {
	
	@JsonProperty
	String switchUid;
	
	@JsonProperty
	String slaveId;
	
	@JsonProperty
	boolean status;
	
	@JsonProperty
	String tag;

	public String getSwitchUid() {
		return switchUid;
	}

	public void setSwitchUid(String switchUid) {
		this.switchUid = switchUid;
	}

	public String getSlaveId() {
		return slaveId;
	}

	public void setSlaveId(String slaveId) {
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
	
	
}
