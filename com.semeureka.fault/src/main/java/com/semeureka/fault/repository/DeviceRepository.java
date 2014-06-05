package com.semeureka.fault.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.semeureka.fault.entity.Device;

public interface DeviceRepository extends JpaRepository<Device, Integer> {
	Device findByCode(byte[] code);
}
