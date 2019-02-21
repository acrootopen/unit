package com.acroot.iot.home.unit.entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="slave", schema="unitmemory")
public class SlaveEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "users_seq_gen")
	@SequenceGenerator(name = "users_seq_gen", sequenceName = "users_id_seq", allocationSize=12, initialValue=1)
	@Column(name="slaveid")
	Long slaveId;
	
	@Column(name="slaveuid")
	String slaveUID;
	
	@Column(name="secretkey")
	String secretKey;
	
	@Column(name="ipaddress")
	String ipAddress;
	
	@Column(name="health")
	boolean health;

	@OneToMany(mappedBy="slaveEntity")
	List<RemoteSwitchesEntity> remoteSwitchesEntities;
	
	
	
	public List<RemoteSwitchesEntity> getRemoteSwitchesEntities() {
		return remoteSwitchesEntities;
	}

	public void setRemoteSwitchesEntities(List<RemoteSwitchesEntity> remoteSwitchesEntities) {
		this.remoteSwitchesEntities = remoteSwitchesEntities;
	}

	public Long getSlaveId() {
		return slaveId;
	}

	public void setSlaveId(Long slaveId) {
		this.slaveId = slaveId;
	}

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
