package com.semeureka.fault.service;

import java.util.List;

import com.semeureka.fault.entity.Line;

public interface LineService {
	Line save(Line line);

	void delete(Integer id);

	Line findOne(Integer id);

	List<Line> findAll();
}
