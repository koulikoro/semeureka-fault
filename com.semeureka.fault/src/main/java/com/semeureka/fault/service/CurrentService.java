package com.semeureka.fault.service;

import java.util.Observer;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.semeureka.fault.entity.Current;

public interface CurrentService extends Observer {
	Page<Current> findAll(Current example, Pageable pageable);
}
