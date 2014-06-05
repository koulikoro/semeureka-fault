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
@Table(name = "T_FAULT_CURRENT")
@JsonIgnoreProperties({ "hibernateLazyInitializer" })
public class Current implements Serializable {
	@Id
	@GeneratedValue
	private Integer id;
	@Column(name = "CURRENT_VALUE")
	private Integer value;
	@Column(name = "CURRENT_TEMPERATURE")
	private Integer temperature;
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

	public Integer getTemperature() {
		return temperature;
	}

	public void setTemperature(Integer temperature) {
		this.temperature = temperature;
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

	public Current() {
	}

	public Current(Integer value, Integer temperature, Device device, Date createTime) {
		this.value = value;
		this.temperature = temperature;
		if (device != null) {
			this.group = device.getGroup();
			this.phase = device.getPhase();
		}
		this.createTime = createTime;
	}
}
