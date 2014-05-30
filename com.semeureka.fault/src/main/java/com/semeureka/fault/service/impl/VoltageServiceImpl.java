package com.semeureka.fault.service.impl;

import static com.semeureka.fault.repository.spec.VoltageSpecifications.byExample;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.semeureka.fault.entity.Voltage;
import com.semeureka.fault.repository.VoltageRepository;
import com.semeureka.fault.service.VoltageService;

@Service
@Transactional
public class VoltageServiceImpl implements VoltageService {
	@Autowired
	private VoltageRepository voltageRepository;

	@Override
	public Voltage save(Voltage voltage) {
		return voltageRepository.save(voltage);
	}

	@Override
	public Page<Voltage> findAll(Voltage example, Pageable pageable) {
		return voltageRepository.findAll(byExample(example), pageable);
	}
}
