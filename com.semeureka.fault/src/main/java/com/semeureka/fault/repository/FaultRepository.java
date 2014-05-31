package com.semeureka.fault.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.semeureka.fault.entity.Fault;

public interface FaultRepository extends JpaRepository<Fault, Integer>,
		JpaSpecificationExecutor<Fault> {
}