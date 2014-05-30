package com.semeureka.fault.entity;

import java.io.Serializable;
import java.util.Map;
import java.util.Set;

import javax.persistence.Cacheable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapKey;
import javax.persistence.MapKeyEnumerated;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.semeureka.fault.entity.Device.Phase;

@Entity
@Table(name = "T_FAULT_GROUP")
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@JsonIgnoreProperties({ "hibernateLazyInitializer", "devices", "children", "parent" })
public class Group implements Serializable {
	public enum Model {
		I, II, III;
	}

	@Id
	@GeneratedValue
	private Integer id;
	@Column(name = "GROUP_HOSTCODE")
	private String hostCode;
	@Column(name = "GROUP_MODEL")
	private Model model;
	@Column(name = "GROUP_NUMBER")
	private Integer number;
	@Column(name = "GROUP_NAME")
	private String location;
	@OneToMany(mappedBy = "group", cascade = CascadeType.ALL)
	@MapKeyEnumerated
	@MapKey(name = "phase")
	@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
	private Map<Phase, Device> devices;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "PARENT_ID")
	private Group parent;
	@OneToMany(mappedBy = "parent", cascade = CascadeType.REMOVE)
	private Set<Group> children;
	@ManyToOne
	@JoinColumn(name = "Line_ID")
	private Line line;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getHostCode() {
		return hostCode;
	}

	public void setHostCode(String hostCode) {
		this.hostCode = hostCode;
	}

	public Model getModel() {
		return model;
	}

	public void setModel(Model model) {
		this.model = model;
	}

	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

	public String getLocation() {
		return location;
	}

	public Map<Phase, Device> getDevices() {
		return devices;
	}

	public void setDevices(Map<Phase, Device> devices) {
		this.devices = devices;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public Group getParent() {
		return parent;
	}

	public void setParent(Group parent) {
		this.parent = parent;
	}

	public Set<Group> getChildren() {
		return children;
	}

	public void setChildren(Set<Group> children) {
		this.children = children;
	}

	public Line getLine() {
		return line;
	}

	public void setLine(Line line) {
		this.line = line;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		Group other = (Group) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Group [id=" + id + ", hostCode=" + hostCode + "]";
	}
}
