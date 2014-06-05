package com.semeureka.fault.entity;

import java.io.Serializable;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.semeureka.frame.misc.ByteUtil;

@Entity
@Table(name = "T_FAULT_DEVICE")
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@JsonIgnoreProperties({ "hibernateLazyInitializer" })
public class Device implements Serializable {
	public enum Phase {
		M, A, B, C;
	}

	@Id
	@GeneratedValue
	private Integer id;
	@Column(name = "DEVICE_CODE", unique = true)
	private byte[] code;
	@Transient
	private String codeHex;
	@Enumerated
	@Column(name = "DEVICE_PHASE", updatable = false, nullable = false)
	private Phase phase;
	@ManyToOne
	@JoinColumn(name = "GROUP_ID", updatable = false, nullable = false)
	private Group group;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public byte[] getCode() {
		return code;
	}

	public void setCode(byte[] code) {
		this.code = code;
		this.codeHex = null;
	}

	public String getCodeHex() {
		if (codeHex == null && code != null) {
			codeHex = ByteUtil.toHex(code);
		}
		return codeHex;
	}

	public void setCodeHex(String codeHex) {
		this.code = ByteUtil.toBytes(codeHex);
		this.codeHex = codeHex;
	}

	public Phase getPhase() {
		return phase;
	}

	public void setPhase(Phase phase) {
		this.phase = phase;
	}

	public Group getGroup() {
		return group;
	}

	public void setGroup(Group group) {
		this.group = group;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((group == null) ? 0 : group.hashCode());
		result = prime * result + ((phase == null) ? 0 : phase.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Device other = (Device) obj;
		if (group == null) {
			if (other.group != null)
				return false;
		} else if (!group.equals(other.group))
			return false;
		if (phase != other.phase)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Device [code=" + code + ", phase=" + phase + ", group=" + group + "]";
	}
}
