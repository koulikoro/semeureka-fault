package com.semeureka.fault.service;

import java.util.Observer;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.semeureka.fault.entity.Voltage;

public interface VoltageService extends Observer {
	Page<Voltage> findAll(Voltage example, Pageable pageable);
}
