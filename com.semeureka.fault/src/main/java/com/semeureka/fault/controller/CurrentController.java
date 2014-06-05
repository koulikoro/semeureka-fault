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

import com.semeureka.fault.entity.Current;
import com.semeureka.fault.service.CurrentService;

@Controller
@RequestMapping("/current")
public class CurrentController {
	@Autowired
	private CurrentService currentService;

	@RequestMapping(value = "")
	public String findAll(@ModelAttribute("example") Current example, Model model, @SortDefaults({
			@SortDefault(direction = Direction.DESC, sort = "createTime"),
			@SortDefault(sort = "phase") }) Pageable pageable) {
		Page<Current> currents = currentService.findAll(example, pageable);
		model.addAttribute("currents", currents);
		return "/current/current";
	}
}
