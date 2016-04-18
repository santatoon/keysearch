package santatoon.wand.web;

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

import santatoon.wand.domain.Admin;
import santatoon.wand.service.AdminService;
import santatoon.wand.web.security.LoginInfo;
import santatoon.wand.web.validator.AdminRegisterValidator;

@Controller
@Transactional
@RequestMapping("/admin/edit/{id}")
@SessionAttributes("admin")
public class AdminEditController{
	private AdminService adminService;
	private AdminRegisterValidator registerValidator;
	private @Inject
	Provider<LoginInfo> loginInfoProvider;

	@Autowired
	public void init(AdminService adminService,
			AdminRegisterValidator registerValidator) {
		this.adminService = adminService;
		this.registerValidator = registerValidator;
	}

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.setDisallowedFields("id", "logins");
	}

	@RequestMapping(method = RequestMethod.GET)
	public String showform(@PathVariable String id, ModelMap model) {
		if (!id.equals(loginInfoProvider.get().currentUser().getId()))
			return "./accessdenied_back";
		else {
			model.addAttribute(this.adminService.get(id));
			return "admin/edit";
		}
	}

	@RequestMapping(method = RequestMethod.POST)
	public String edit(@ModelAttribute @Valid Admin admin,
			BindingResult result, SessionStatus status, HttpServletRequest request) throws Exception {
		// this.registerValidator.validate(admin, result);
		if (result.hasErrors()) {
			return "admin/edit";
		} else {

			this.adminService.update(admin);
			loginInfoProvider.get().save(admin);
			status.setComplete();
			return "redirect:../list";
		}
	}
}
