package com.semeureka.fault.service.impl;

import static com.semeureka.fault.repository.spec.CurrentSpecifications.byExample;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.semeureka.fault.entity.Current;
import com.semeureka.fault.repository.CurrentRepository;
import com.semeureka.fault.service.CurrentService;

@Service
@Transactional
public class CurrentServiceImpl implements CurrentService {
	@Autowired
	private CurrentRepository currentRepository;

	@Override
	public Current save(Current current) {
		return currentRepository.save(current);
	}

	@Override
	public Page<Current> findAll(Current example, Pageable pageable) {
		return currentRepository.findAll(byExample(example), pageable);
	}
}
