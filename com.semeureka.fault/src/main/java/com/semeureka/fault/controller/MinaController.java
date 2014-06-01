package com.semeureka.fault.controller;

import java.io.IOException;
import java.net.SocketAddress;

import org.apache.mina.core.service.IoAcceptor;
import org.apache.mina.core.service.IoConnector;
import org.apache.mina.transport.serial.SerialAddress;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.semeureka.fault.mina.MinalService;
import com.semeureka.frame.misc.SocketAddressEditor;

@Controller
@RequestMapping("/mina")
public class MinaController {
	@Autowired
	private MinalService minalService;

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(SocketAddress.class, new SocketAddressEditor());
	}

	@RequestMapping(value = "")
	public String findAll(Model model) {
		model.addAttribute("acceptor", minalService.getAcceptor());
		model.addAttribute("connector", minalService.getConnector());
		model.addAttribute("serial", minalService.getSerial());
		model.addAttribute("commPosts", minalService.getCommPorts());
		return "/mina/mina";
	}

	@RequestMapping(value = "/acceptor", method = RequestMethod.POST)
	public String acceptorToggle(@RequestParam SocketAddress defaultAddress,
			RedirectAttributes model) {
		IoAcceptor acceptor = minalService.getAcceptor();
		try {
			minalService.toggle(acceptor, defaultAddress);
		} catch (IOException e) {
			model.addFlashAttribute("acceptorError", e);
		}
		model.addAttribute("tab", "acceptor");
		return "redirect:/mina";
	}

	@RequestMapping(value = "/connector", method = RequestMethod.POST)
	public String connectorToggle(@RequestParam SocketAddress defaultAddress,
			RedirectAttributes model) {
		IoConnector connector = minalService.getConnector();
		try {
			minalService.toggle(connector, defaultAddress);
		} catch (IOException e) {
			model.addFlashAttribute("connectorError", e);
		}
		model.addAttribute("tab", "connector");
		return "redirect:/mina";
	}

	@RequestMapping(value = "/serial", method = RequestMethod.POST)
	public String serialToggle(@RequestParam(required = false) SerialAddress defaultAddress,
			RedirectAttributes model) {
		IoConnector serial = minalService.getSerial();
		try {
			minalService.toggle(serial, defaultAddress);
		} catch (IOException e) {
			model.addFlashAttribute("serialError", e);
		}
		model.addAttribute("tab", "serial");
		return "redirect:/mina";
	}
}
