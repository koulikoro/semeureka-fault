package com.semeureka.fault.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.semeureka.fault.entity.Current;

public interface CurrentService {
	Current save(Current current);

	Page<Current> findAll(Current example, Pageable pageable);
}
