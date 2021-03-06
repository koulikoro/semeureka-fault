package com.semeureka.fault.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.semeureka.fault.entity.Rawdata;

public interface RawdataRepository extends JpaRepository<Rawdata, Integer>,
		JpaSpecificationExecutor<Rawdata> {
}