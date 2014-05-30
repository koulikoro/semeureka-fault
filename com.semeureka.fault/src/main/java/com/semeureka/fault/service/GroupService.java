package com.semeureka.fault.service;

import java.util.List;

import com.semeureka.fault.entity.Group;

public interface GroupService {
	Group save(Group group);

	void delete(Integer id);

	Group findOne(Integer id);

	List<Group> findAll();

	List<Group> findAll(Group group);
}
