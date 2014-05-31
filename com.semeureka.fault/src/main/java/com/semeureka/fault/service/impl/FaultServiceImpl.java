package com.semeureka.fault.service.impl;

import static com.semeureka.fault.repository.spec.FaultSpecifications.byExample;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.semeureka.fault.entity.Fault;
import com.semeureka.fault.repository.FaultRepository;
import com.semeureka.fault.service.FaultService;

@Service
@Transactional
public class FaultServiceImpl implements FaultService {
	@Autowired
	private FaultRepository faultRepository;

	@Override
	public Fault save(Fault fault) {
		return faultRepository.save(fault);
	}

	@Override
	public Page<Fault> findAll(Fault example, Pageable pageable) {
		return faultRepository.findAll(byExample(example), pageable);
	}
}
