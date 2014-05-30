package com.semeureka.fault.service;

import com.semeureka.fault.entity.Device;

public interface DeviceService {
	Device findByCode(String code);
}
