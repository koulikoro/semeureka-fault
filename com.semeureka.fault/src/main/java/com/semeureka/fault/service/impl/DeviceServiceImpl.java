package com.semeureka.fault.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.semeureka.fault.entity.Device;
import com.semeureka.fault.repository.DeviceRepository;
import com.semeureka.fault.service.DeviceService;

@Service
@Transactional
public class DeviceServiceImpl implements DeviceService {
	@Autowired
	private DeviceRepository deviceRepository;

	@Override
	public Device findByCode(byte[] code) {
		return deviceRepository.findByCode(code);
	}
}
