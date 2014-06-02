package com.semeureka.fault.controller;

import java.io.IOException;
import java.net.SocketAddress;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.semeureka.fault.mina.MinaService;

@Controller
@RequestMapping("/mina")
public class MinaController {
	@Autowired
	private MinaService minaService;

	@RequestMapping(value = "")
	public String findAll(Model model) {
		model.addAttribute("server", minaService.getServer());
		model.addAttribute("client", minaService.getClient());
		model.addAttribute("serial", minaService.getSerial());
		model.addAttribute("commPosts", minaService.getCommPorts());
		return "/mina/mina";
	}

	@RequestMapping(value = "/server", method = RequestMethod.POST)
	public String serverToggle(@RequestParam SocketAddress address, RedirectAttributes model) {
		minaService.setServerAddress(address);
		minaService.setServerStart(!minaService.getServer().isActive());
		try {
			minaService.toggleServer();
		} catch (IOException e) {
			model.addFlashAttribute("serverError", e);
		}
		model.addAttribute("tab", "server");
		return "redirect:/mina";
	}

	@RequestMapping(value = "/client", method = RequestMethod.POST)
	public String clientToggle(@RequestParam SocketAddress address, RedirectAttributes model) {
		minaService.setClientAddress(address);
		minaService.setClientStart(!minaService.getClient().isActive());
		try {
			minaService.toggleClient();
		} catch (IOException e) {
			model.addFlashAttribute("clientError", e);
		}
		model.addAttribute("tab", "client");
		return "redirect:/mina";
	}

	@RequestMapping(value = "/serial", method = RequestMethod.POST)
	public String serialToggle(@RequestParam SocketAddress address, RedirectAttributes model) {
		minaService.setSerialAddress(address);
		minaService.setSerialStart(!minaService.getSerial().isActive());
		try {
			minaService.toggleSerial();
		} catch (IOException e) {
			model.addFlashAttribute("serialError", e);
		}
		model.addAttribute("tab", "serial");
		return "redirect:/mina";
	}
}
