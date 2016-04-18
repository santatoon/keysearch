package santatoon.wand.web;

import javax.inject.Inject;
import javax.inject.Provider;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import santatoon.wand.domain.Admin;
import santatoon.wand.service.AdminService;
import santatoon.wand.web.image.ImageControll;
import santatoon.wand.web.security.LoginInfo;

@Controller
@Transactional
@RequestMapping("/admin")
public class AdminController extends ImageControll{
	@Autowired
	private AdminService adminService;
	@Inject
	private Provider<LoginInfo> loginInfoProvider;

	@ModelAttribute("currentUser")
	public Admin currentUser() {
		return loginInfoProvider.get().currentUser();
	}
	

	@RequestMapping("/list")
	public String list(ModelMap model) {
		model.addAttribute(this.adminService.getAll());
		return "admin/list";
	}

	@RequestMapping("/view/{id}")
	public String list(@PathVariable String id, ModelMap model) {
		model.addAttribute(this.adminService.get(id));
		return "admin/view";
	}

	@RequestMapping("/delete/{id}")
	public String delete(@PathVariable String id, HttpServletRequest request) {
		if (!id.equals(loginInfoProvider.get().currentUser().getId()))
			return "./accessdenied_back";
		else {
			this.adminService.delete(id);
			loginInfoProvider.get().remove();
			return "admin/deleted";
		}
	}
}
