package santatoon.wand.web;

import javax.inject.Inject;
import javax.inject.Provider;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import santatoon.wand.domain.Admin;
import santatoon.wand.service.DeviceService;
import santatoon.wand.web.security.LoginInfo;

@Controller
@Transactional
@RequestMapping("/devices")
public class DeviceController {
	@Autowired
	private DeviceService deviceService;
	@Inject
	private Provider<LoginInfo> loginInfoProvider;

	@ModelAttribute("currentUser")
	public Admin currentUser() {
		return loginInfoProvider.get().currentUser();
	}

	@RequestMapping("/push/{id}")
	public String push(@PathVariable int id, Model model) {

		try {
			this.deviceService.pushToDevices(id);
			model.addAttribute("result", "PUSH 전송에 성공하였습니다.");
			model.addAttribute("link", "../list");
			return "result";
		} catch (Exception e) {
			model.addAttribute("result", "PUSH 전송에 실패하였습니다.");
			model.addAttribute("link", "javascript:history.back()");
			return "result";
		}
	}

	@RequestMapping("/list")
	public String list(ModelMap model) {
		model.addAttribute(this.deviceService.getAll());
		return "devices/list";
	}

	@RequestMapping("/view/{id}")
	public String list(@PathVariable int id, ModelMap model) {
		model.addAttribute(this.deviceService.get(id));
		return "devices/view";
	}

	@RequestMapping("/delete/{id}")
	public String delete(@PathVariable int id, HttpServletRequest request) {
		this.deviceService.delete(id);
		return "devices/deleted";
	}
}
