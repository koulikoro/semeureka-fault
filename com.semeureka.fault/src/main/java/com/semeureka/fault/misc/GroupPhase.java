package com.semeureka.fault.misc;

import java.util.Date;

import com.semeureka.fault.entity.Device.Phase;
import com.semeureka.fault.entity.Group;

public interface GroupPhase {
	public Group getGroup();

	public Phase getPhase();

	public Date getCreateTime();
}
