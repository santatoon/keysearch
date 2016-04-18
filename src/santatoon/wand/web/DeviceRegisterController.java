package santatoon.wand.web;


import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import santatoon.wand.domain.Status;
import santatoon.wand.domain.Device;
import santatoon.wand.service.DeviceService;

@Controller
@Transactional
@RequestMapping("/devices/register")
@SessionAttributes("device")
public class DeviceRegisterController{
	private DeviceService deviceService;
	
	@Autowired
	public void init(DeviceService deviceService) {
		this.deviceService = deviceService;
	}
	
	@ModelAttribute
	public List<Status> statuses() {
		Status[] status = Status.values();
		return Arrays.asList(status);
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public String showform(ModelMap model) {
		model.addAttribute(new Device());
		return "devices/register";
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public String register(@ModelAttribute @Valid Device device, BindingResult result, SessionStatus status, HttpServletRequest request) throws Exception{
		if (result.hasErrors()) {
			return "devices/register";
		}
		else {
			int deviceId = this.deviceService.add(device);
			device.setId(deviceId);
			device.initDates();
			this.deviceService.update(device);				
			status.setComplete();
			return "redirect:list";
		}
	}
}
