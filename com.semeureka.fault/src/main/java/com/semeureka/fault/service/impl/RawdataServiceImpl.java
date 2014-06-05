package com.semeureka.fault.service.impl;

import java.util.Observable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.semeureka.fault.entity.Rawdata;
import com.semeureka.fault.repository.RawdataRepository;
import com.semeureka.fault.service.RawdataService;

@Service("rawdataService")
@Transactional
public class RawdataServiceImpl implements RawdataService {
	@Autowired
	private RawdataRepository rawdataRepository;

	@Override
	public void update(Observable o, Object arg) {
		if (arg instanceof Rawdata) {
			Rawdata rawdata = (Rawdata) arg;
			rawdataRepository.save(rawdata);
		}
	}
}
