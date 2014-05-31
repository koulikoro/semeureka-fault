package com.semeureka.fault.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.semeureka.fault.entity.Fault;
import com.semeureka.fault.service.FaultService;

@Controller
@RequestMapping("/fault")
public class FaultController {
	@Autowired
	private FaultService faultService;

	@RequestMapping(value = "")
	public String findAll(@ModelAttribute("example") Fault example, Model model, Pageable pageable) {
		Page<Fault> faults = faultService.findAll(example, pageable);
		model.addAttribute("faults", faults);
		return "/fault/fault";
	}
}
