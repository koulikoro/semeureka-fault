package com.semeureka.fault.service;

import java.util.Date;
import java.util.Observer;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.semeureka.fault.entity.Warn;

public interface WarnService extends Observer {
	Page<Warn> findAll(Integer lineId, Integer groupId, Date createTime, Pageable pageable);
}
