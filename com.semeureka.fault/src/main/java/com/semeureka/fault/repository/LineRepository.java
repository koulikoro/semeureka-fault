package com.semeureka.fault.repository;

import java.util.List;

import javax.persistence.QueryHint;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.QueryHints;

import com.semeureka.fault.entity.Line;

public interface LineRepository extends JpaRepository<Line, Integer> {
	@Override
	@QueryHints(@QueryHint(name = "org.hibernate.cacheable", value = "true"))
	public List<Line> findAll();
}
