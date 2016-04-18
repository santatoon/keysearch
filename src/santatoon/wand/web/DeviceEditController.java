package santatoon.wand.web;

import java.io.File;
import java.sql.Date;
import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Provider;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;

import santatoon.wand.domain.Device;
import santatoon.wand.domain.Status;
import santatoon.wand.service.DeviceService;
import santatoon.wand.web.image.ImageControll;
import santatoon.wand.web.security.LoginInfo;

@Controller
@Transactional
@RequestMapping("/devices/edit/{id}")
@SessionAttributes("device")
public class DeviceEditController{
	private DeviceService deviceService;
	private @Inject
	Provider<LoginInfo> loginInfoProvider;

	@Autowired
	public void init(DeviceService deviceService) {
		this.deviceService = deviceService;
	}
	
	@ModelAttribute
	public List<Status> statuses() {
		Status[] status = Status.values();
		return Arrays.asList(status);
	}

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.setDisallowedFields("id", "logins");
	}

	@RequestMapping(method = RequestMethod.GET)
	public String showform(@PathVariable int id, ModelMap model) {
		model.addAttribute(this.deviceService.get(id));
		return "devices/edit";
	}

	@RequestMapping(method = RequestMethod.POST)
	public String edit(@ModelAttribute @Valid Device device,
			BindingResult result, SessionStatus status, HttpServletRequest request) throws Exception {
		if (result.hasErrors()) {
			return "devices/edit";
		} else {
			device.setModified(new Date(new java.util.Date().getTime()));
			this.deviceService.update(device);
			status.setComplete();
			return "redirect:../list";
		}
	}
}
