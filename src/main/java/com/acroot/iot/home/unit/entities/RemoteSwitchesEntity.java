package com.acroot.iot.home.unit.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "remoteswitches", schema = "unitmemory")
public class RemoteSwitchesEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "users_seq_gen")
	@SequenceGenerator(name = "users_seq_gen", sequenceName = "users_id_seq", allocationSize = 12, initialValue = 1)
	Long switchId;

	@Column(name = "switchuid")
	String switchUid;

	@Column(name = "status")
	boolean status;

	@Column
	String tag;

	
	@ManyToOne
	@JoinColumn(name="slaveId", nullable=false)
	SlaveEntity slaveEntity;

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

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

}
