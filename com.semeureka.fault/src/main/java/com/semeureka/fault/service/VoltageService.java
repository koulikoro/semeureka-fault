package com.semeureka.fault.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.semeureka.fault.entity.Voltage;

public interface VoltageService {
	Voltage save(Voltage voltage);

	Page<Voltage> findAll(Voltage example, Pageable pageable);
}
