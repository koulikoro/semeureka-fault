package com.semeureka.fault.service.impl;

import java.util.Date;
import java.util.Observable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.semeureka.fault.entity.Warn;
import com.semeureka.fault.repository.WarnRepository;
import com.semeureka.fault.service.WarnService;

@Service
@Transactional
public class WarnServiceImpl implements WarnService {
	@Autowired
	private WarnRepository repository;

	@Override
	public void update(Observable o, Object arg) {
		if (arg instanceof Warn) {
			Warn warn = (Warn) arg;
			repository.save(warn);
		}
	}

	@Override
	public Page<Warn> findAll(Integer lineId, Integer groupId, Date createTime, Pageable pageable) {
		return repository.findAll(lineId, groupId, createTime, pageable);
	}
}
