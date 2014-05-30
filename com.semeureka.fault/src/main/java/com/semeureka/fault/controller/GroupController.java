package com.semeureka.fault.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.semeureka.fault.entity.Device.Phase;
import com.semeureka.fault.entity.Group;
import com.semeureka.fault.service.GroupService;

@Controller
@RequestMapping("/group")
public class GroupController {
	@Autowired
	private GroupService groupService;

	@RequestMapping(value = "", method = RequestMethod.POST)
	public String create(Group group) {
		groupService.save(group);
		return "redirect:/group";
	}

	@RequestMapping(value = "/{id}/delete")
	public String delete(@PathVariable("id") Integer id) {
		groupService.delete(id);
		return "redirect:/group";
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.POST)
	public String update(@PathVariable("id") Integer id, Group group) {
		group.setId(id);
		groupService.save(group);
		return "redirect:/group";
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public String find(@PathVariable("id") Integer id, Model model) {
		Group group = groupService.findOne(id);
		model.addAttribute("group", group);
		model.addAttribute("phases", Phase.values());
		return "/group/update";
	}

	@RequestMapping(value = "", method = RequestMethod.GET)
	public String findAll(Model model) {
		List<Group> groups = groupService.findAll();
		model.addAttribute("groups", groups);
		model.addAttribute("phases", Phase.values());
		return "/group/group";
	}

	@RequestMapping(value = "", produces = "application/json")
	@ResponseBody
	public List<Group> findAll(Group group, Model model) {
		return groupService.findAll(group);
	}
}
