package com.semeureka.fault.repository;

import java.util.Date;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import com.semeureka.fault.entity.Warn;

public interface WarnRepository extends JpaRepository<Warn, Integer>,
		JpaSpecificationExecutor<Warn> {
	@Query("select w from Warn w where (?1 is null or w.group.line.id = ?1)"
			+ " and (?2 is null or w.group.id = ?2)")
	Page<Warn> findAll(Integer lineId, Integer groupId, Date createTime, Pageable pageable);
}