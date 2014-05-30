package com.semeureka.fault.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.semeureka.fault.entity.Line;
import com.semeureka.fault.repository.LineRepository;
import com.semeureka.fault.service.LineService;

@Service("lineService")
@Transactional
public class LineServiceImpl implements LineService {
	@Autowired
	private LineRepository lineRepository;

	@Override
	public Line save(Line line) {
		return lineRepository.save(line);
	}

	@Override
	public void delete(Integer id) {
		lineRepository.delete(id);
	}

	@Override
	public Line findOne(Integer id) {
		return lineRepository.findOne(id);
	}

	@Override
	public List<Line> findAll() {
		return lineRepository.findAll();
	}
}
