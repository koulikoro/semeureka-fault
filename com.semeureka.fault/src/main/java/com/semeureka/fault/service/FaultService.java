package com.semeureka.fault.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.semeureka.fault.entity.Fault;

public interface FaultService {
	Fault save(Fault voltage);

	Page<Fault> findAll(Fault example, Pageable pageable);

	Fault findOne(Integer id);

	void update(Fault fault);
}
