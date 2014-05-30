package com.semeureka.fault.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.semeureka.fault.entity.Voltage;

public interface VoltageRepository extends JpaRepository<Voltage, Integer>,
		JpaSpecificationExecutor<Voltage> {
}