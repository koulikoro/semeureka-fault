package com.semeureka.fault.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.semeureka.fault.entity.Station;
import com.semeureka.fault.service.StationService;

@Controller
@RequestMapping("/station")
public class StationController {
	@Autowired
	private StationService stationService;

	@RequestMapping(value = "", method = RequestMethod.POST)
	public String save(Station station) {
		stationService.save(station);
		return "redirect:/station";
	}

	@RequestMapping(value = "/{id}/delete")
	public String delete(@PathVariable("id") Integer id) {
		stationService.delete(id);
		return "redirect:/station";
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.POST)
	public String update(@PathVariable("id") Integer id, Station station) {
		station.setId(id);
		stationService.save(station);
		return "redirect:/station";
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public String find(@PathVariable("id") Integer id, Model model) {
		Station station = stationService.findOne(id);
		model.addAttribute("station", station);
		return "/station/update";
	}

	@RequestMapping(value = "", method = RequestMethod.GET)
	public String findAll(Model model) {
		List<Station> stations = stationService.findAll();
		model.addAttribute("stations", stations);
		return "/station/station";
	}
}
