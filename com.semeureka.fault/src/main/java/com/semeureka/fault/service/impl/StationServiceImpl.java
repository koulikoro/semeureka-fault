package com.semeureka.fault.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.semeureka.fault.entity.Station;
import com.semeureka.fault.repository.StationRepository;
import com.semeureka.fault.service.StationService;

@Service("stationService")
@Transactional
public class StationServiceImpl implements StationService {
	@Autowired
	private StationRepository stationRepository;

	@Override
	public Station save(Station station) {
		return stationRepository.save(station);
	}

	@Override
	public void delete(Integer id) {
		stationRepository.delete(id);
	}

	@Override
	public Station findOne(Integer id) {
		return stationRepository.findOne(id);
	}

	@Override
	public List<Station> findAll() {
//		return stationRepository.findByIdNotNull();
		return stationRepository.findAll();
	}
}
