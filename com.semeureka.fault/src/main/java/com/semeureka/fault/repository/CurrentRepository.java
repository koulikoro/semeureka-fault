package com.semeureka.fault.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.semeureka.fault.entity.Current;

public interface CurrentRepository extends JpaRepository<Current, Integer>,
		JpaSpecificationExecutor<Current> {
}