package com.semeureka.fault.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.SortDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.semeureka.fault.entity.Warn;
import com.semeureka.fault.service.WarnService;

@Controller
@RequestMapping("/warn")
public class WarnController {
	@Autowired
	private WarnService warnService;

	@RequestMapping(value = "")
	public String findAll(Integer lineId, Integer groupId, Date createTime, Model model,
			@SortDefault(sort = "createTime", direction = Direction.DESC) Pageable pageable) {
		Page<Warn> warns = warnService.findAll(lineId, groupId, new Date(), pageable);
		model.addAttribute("warns", warns);
		return "/warn/warn";
	}
}
