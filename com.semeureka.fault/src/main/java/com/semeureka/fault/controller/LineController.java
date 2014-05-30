package com.semeureka.fault.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.semeureka.fault.entity.Line;
import com.semeureka.fault.service.LineService;

@Controller
@RequestMapping("/line")
public class LineController {
	@Autowired
	private LineService lineService;

	@RequestMapping(value = "", method = RequestMethod.POST)
	public String create(Line line) {
		lineService.save(line);
		return "redirect:/line";
	}

	@RequestMapping(value = "/{id}/delete")
	public String delete(@PathVariable("id") Integer id) {
		lineService.delete(id);
		return "redirect:/line";
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.POST)
	public String update(@PathVariable("id") Integer id, Line line) {
		line.setId(id);
		lineService.save(line);
		return "redirect:/line";
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public String find(@PathVariable("id") Integer id, Model model) {
		Line line = lineService.findOne(id);
		model.addAttribute("line", line);
		return "/line/update";
	}

	@RequestMapping(value = "", method = RequestMethod.GET)
	public String findAll(Model model) {
		List<Line> lines = lineService.findAll();
		model.addAttribute("lines", lines);
		return "/line/line";
	}
}
