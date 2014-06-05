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
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.semeureka.fault.entity.Device.Phase;
import com.semeureka.frame.misc.ByteUtil;

@Entity
@Table(name = "T_FAULT_RAWDATA")
@JsonIgnoreProperties({ "hibernateLazyInitializer" })
public class Rawdata implements Serializable {
	@Id
	@GeneratedValue
	private Integer id;
	@Column(name = "RAWDATA_CONTENT")
	private byte[] content;
	@Transient
	private String contentHex;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "GROUP_ID")
	private Group group;
	@Enumerated
	@Column(name = "DEVICE_PHASE")
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

	public byte[] getContent() {
		return content;
	}

	public void setContent(byte[] content) {
		this.content = content;
		this.contentHex = null;
	}

	public String getContentHex() {
		if (contentHex == null && content != null) {
			contentHex = ByteUtil.toHex(content);
		}
		return contentHex;
	}

	public void setContentHex(String contentHex) {
		content = ByteUtil.toBytes(contentHex);
		this.contentHex = contentHex;
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

	public Rawdata() {
	}

	public Rawdata(byte[] content, Group group, Phase phase, Date createTime) {
		this.content = content;
		this.group = group;
		this.phase = phase;
		this.createTime = createTime;
	}
}
