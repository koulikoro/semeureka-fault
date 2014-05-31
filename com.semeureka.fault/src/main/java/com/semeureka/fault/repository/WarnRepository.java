package com.semeureka.fault.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.semeureka.fault.entity.Warn;

public interface WarnRepository extends JpaRepository<Warn, Integer>,
		JpaSpecificationExecutor<Warn> {
}