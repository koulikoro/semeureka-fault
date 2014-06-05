package com.semeureka.fault.controller;

import java.io.IOException;
import java.net.SocketAddress;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.semeureka.fault.comm.CommSupport;

@Controller
@RequestMapping("/comm")
public class CommController {
	@Autowired
	private CommSupport commSupport;

	@RequestMapping(value = "")
	public String findAll(Model model) {
		model.addAttribute("server", commSupport.getServer());
		model.addAttribute("serverStart", commSupport.isServerStart());
		model.addAttribute("client", commSupport.getClient());
		model.addAttribute("clientStart", commSupport.isClientStart());
		model.addAttribute("serial", commSupport.getSerial());
		model.addAttribute("serialStart", commSupport.isSerialStart());
		model.addAttribute("project", commSupport.getProject());
		model.addAttribute("commPosts", commSupport.getCommPorts());
		return "/comm/comm";
	}

	@RequestMapping(value = "/{service}/stop", method = RequestMethod.GET)
	public String stopService(@PathVariable String service, RedirectAttributes model) {
		try {
			if ("server".equals(service)) {
				commSupport.setServerStart(false);
				commSupport.toggleServer();
			} else if ("client".equals(service)) {
				commSupport.setClientStart(false);
				commSupport.toggleClient();
			} else if ("serial".equals(service)) {
				commSupport.setSerialStart(false);
				commSupport.toggleSerial();
			}
		} catch (IOException e) {
			model.addFlashAttribute(service + "Error", e);
		}
		model.addAttribute("tab", service);
		return "redirect:/comm";
	}

	@RequestMapping(value = "/{service}/start", method = RequestMethod.POST)
	public String startService(@PathVariable String service, @RequestParam SocketAddress address,
			@RequestParam(required = false) Integer project, RedirectAttributes model) {
		try {
			if ("server".equals(service)) {
				commSupport.setServerAddress(address);
				commSupport.setServerStart(true);
				commSupport.toggleServer();
			} else if ("client".equals(service)) {
				commSupport.setClientAddress(address);
				commSupport.setClientStart(true);
				commSupport.setProject(project);
				commSupport.toggleClient();
			} else if ("serial".equals(service)) {
				commSupport.setSerialAddress(address);
				commSupport.setSerialStart(true);
				commSupport.toggleSerial();
			}
		} catch (IOException e) {
			model.addFlashAttribute(service + "Error", e);
		}
		model.addAttribute("tab", service);
		return "redirect:/comm";
	}
}
