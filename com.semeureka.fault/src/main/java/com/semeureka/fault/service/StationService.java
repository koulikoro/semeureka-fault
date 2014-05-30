package com.semeureka.fault.service;

import java.util.List;

import com.semeureka.fault.entity.Station;

public interface StationService {
	Station save(Station station);

	void delete(Integer id);

	Station findOne(Integer id);

	List<Station> findAll();
}
