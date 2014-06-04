package com.semeureka.fault.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.semeureka.fault.entity.Fault;
import com.semeureka.fault.service.FaultService;
import com.semeureka.frame.entity.Alert;

@Controller
@RequestMapping("/fault")
public class FaultController {
	@Autowired
	private FaultService faultService;

	@RequestMapping(value = "")
	public String find(@ModelAttribute("example") Fault example, Model model, Pageable pageable) {
		Page<Fault> faults = faultService.findAll(example, pageable);
		model.addAttribute("faults", faults);
		return "/fault/fault";
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public String find(@PathVariable Integer id, Model model) {
		Fault fault = faultService.findOne(id);
		model.addAttribute("fault", fault);
		return "/fault/update";
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.POST)
	public String update(@PathVariable Integer id, Alert alert, Model model) {
		Fault fault = faultService.findOne(id);
		if (fault.getAlert() != null) {
			fault.getAlert().setComment(alert.getComment());
		} else {
			fault.setAlert(alert);
		}
		fault.getAlert().setUpdateTime(new Date());
		faultService.update(fault);
		return "redirect:/fault";
	}
}
