package com.semeureka.fault.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.semeureka.fault.entity.Device;
import com.semeureka.fault.entity.Group;
import com.semeureka.fault.service.DeviceService;

@Controller
@RequestMapping("/device")
public class DeviceController {
	@Autowired
	private DeviceService deviceService;

	@ResponseBody
	@RequestMapping(value = "/contains")
	public boolean contains(Group group) { // 检查一个终端编码是否已经包含在其它分组
		Device device = deviceService.findByCode(group.getDevices().values().iterator().next()
				.getCode());
		return device == null || device.getGroup().equals(group);
	}
}
