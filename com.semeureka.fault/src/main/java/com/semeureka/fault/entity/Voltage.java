package com.semeureka.fault.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.semeureka.fault.entity.Device.Phase;

@Entity
@Table(name = "T_FAULT_VOLTAGE")
@JsonIgnoreProperties({ "hibernateLazyInitializer" })
public class Voltage implements Serializable {
	@Id
	@GeneratedValue
	private Integer id;
	@Column(name = "VOLTAGE_VALUE")
	private Integer value;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "GROUP_ID", nullable = false)
	private Group group;
	@Enumerated
	@Column(name = "DEVICE_PHASE", nullable = false)
	private Phase phase;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CREATE_TIME")
	private Date createTime;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getValue() {
		return value;
	}

	public void setValue(Integer value) {
		this.value = value;
	}

	public Group getGroup() {
		return group;
	}

	public void setGroup(Group group) {
		this.group = group;
	}

	public Phase getPhase() {
		return phase;
	}

	public void setPhase(Phase phase) {
		this.phase = phase;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Voltage() {
	}

	public Voltage(Integer value, Device device, Date createTime) {
		this.value = value;
		if (device != null) {
			this.group = device.getGroup();
			this.phase = device.getPhase();
		}
		this.createTime = createTime;
	}
}
