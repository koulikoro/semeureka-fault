package com.semeureka.fault.service.impl;

import static com.semeureka.fault.repository.spec.GroupSpecifications.byExample;

import java.util.List;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.semeureka.fault.entity.Device;
import com.semeureka.fault.entity.Device.Phase;
import com.semeureka.fault.entity.Group;
import com.semeureka.fault.entity.Line;
import com.semeureka.fault.repository.GroupRepository;
import com.semeureka.fault.service.GroupService;

@Service("groupService")
@Transactional
public class GroupServiceImpl implements GroupService {
	@Autowired
	private GroupRepository groupRepository;

	@Override
	public Group save(Group group) {
		for (Entry<Phase, Device> entry : group.getDevices().entrySet()) {
			entry.getValue().setGroup(group);
			entry.getValue().setPhase(entry.getKey());
		}
		if (group.getParent() != null && group.getParent().getId() == null) {
			group.setParent(null);
		}
		group = groupRepository.save(group);
		return group;
	}

	@Override
	public void delete(Integer id) {
		groupRepository.delete(id);
	}

	@Override
	public Group findOne(Integer id) {
		return groupRepository.findOne(id);
	}

	@Override
	public List<Group> findAll() {
		return groupRepository.findAll();
	}

	@Override
	public List<Group> findAll(Group example) {
		return groupRepository.findAll(byExample(example));
	}

	@Override
	public List<Group> findByLine(Line line) {
		return groupRepository.findByLine(line);
	}

	@Override
	public Group findByHostCode(byte[] hostCode) {
		return groupRepository.findByHostCode(hostCode);
	}
}
