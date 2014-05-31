package com.semeureka.fault.repository;

import java.util.List;

import javax.persistence.QueryHint;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.QueryHints;

import com.semeureka.fault.entity.Group;
import com.semeureka.fault.entity.Line;

public interface GroupRepository extends JpaRepository<Group, Integer>,
		JpaSpecificationExecutor<Group> {
	@Override
	@QueryHints(@QueryHint(name = "org.hibernate.cacheable", value = "true"))
	public List<Group> findAll();

	@Override
	@QueryHints(@QueryHint(name = "org.hibernate.cacheable", value = "true"))
	public List<Group> findAll(Specification<Group> spec);

	@QueryHints(@QueryHint(name = "org.hibernate.cacheable", value = "true"))
	public List<Group> findByLine(Line line);
}
