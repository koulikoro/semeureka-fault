package com.semeureka.fault.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.SortDefault;
import org.springframework.data.web.SortDefault.SortDefaults;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.semeureka.fault.entity.Voltage;
import com.semeureka.fault.service.VoltageService;

@Controller
@RequestMapping("/voltage")
public class VoltageController {
	@Autowired
	private VoltageService voltageService;

	@RequestMapping(value = "")
	public String findAll(@ModelAttribute("example") Voltage example, Model model, @SortDefaults({
			@SortDefault(direction = Direction.DESC, sort = "createTime"),
			@SortDefault(sort = "phase") }) Pageable pageable) {
		Page<Voltage> voltages = voltageService.findAll(example, pageable);
		model.addAttribute("voltages", voltages);
		return "/voltage/voltage";
	}

	@RequestMapping(value = "/tree")
	public String find() {
		return "/voltage/tree";
	}
}
