package com.semeureka.fault.service;

import java.util.List;

import com.semeureka.fault.entity.Group;
import com.semeureka.fault.entity.Line;

public interface GroupService {
	Group save(Group group);

	void delete(Integer id);

	Group findOne(Integer id);

	List<Group> findAll();

	List<Group> findAll(Group group);

	List<Group> findByLine(Line line);

	Group findByHostCode(byte[] hostCode);
}
