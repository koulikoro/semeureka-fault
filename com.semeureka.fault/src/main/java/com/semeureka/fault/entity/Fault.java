package com.semeureka.fault.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
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
import com.semeureka.fault.entity.Warn.WarnType;

@Entity
@Table(name = "T_FAULT_FAULT")
@JsonIgnoreProperties({ "hibernateLazyInitializer" })
public class Fault implements Serializable {
	@Id
	@GeneratedValue
	private Integer id;
	@Column(name = "WARN_TYPE")
	private WarnType warnType;
	@Enumerated
	@ElementCollection(fetch = FetchType.EAGER)
	@CollectionTable(name = "T_FAULT_FAULT_PHASES", joinColumns = @JoinColumn(name = "FAULT_ID"))
	@Column(name = "DEVICE_PHASE", nullable = false)
	private Set<Phase> phases;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "GROUP_ID", nullable = false)
	private Group group;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CREATE_TIME")
	private Date createTime;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public WarnType getWarnType() {
		return warnType;
	}

	public void setWarnType(WarnType warnType) {
		this.warnType = warnType;
	}

	public Set<Phase> getPhases() {
		return phases;
	}

	public void setPhases(Set<Phase> phases) {
		this.phases = phases;
	}

	public Group getGroup() {
		return group;
	}

	public void setGroup(Group group) {
		this.group = group;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
}
