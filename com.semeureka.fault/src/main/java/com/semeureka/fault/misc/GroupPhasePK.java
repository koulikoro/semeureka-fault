package com.semeureka.fault.misc;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.semeureka.fault.entity.Device.Phase;
import com.semeureka.fault.entity.Group;

@Embeddable
public class GroupPhasePK implements Serializable {
	@ManyToOne
	@JoinColumn(name = "GROUP_ID", updatable = false, nullable = false)
	private Group group;
	@Enumerated
	@Column(name = "DEVICE_PHASE", updatable = false, nullable = false)
	private Phase phase;

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
		GroupPhasePK other = (GroupPhasePK) obj;
		if (group == null) {
			if (other.group != null)
				return false;
		} else if (!group.equals(other.group))
			return false;
		if (phase != other.phase)
			return false;
		return true;
	}
}
