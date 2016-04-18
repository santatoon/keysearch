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

import santatoon.wand.domain.Admin;
import santatoon.wand.service.AdminService;
import santatoon.wand.web.image.ImageControll;
import santatoon.wand.web.validator.AdminRegisterValidator;

@Controller
@Transactional
@RequestMapping("/admin/register")
@SessionAttributes("admin")
public class AdminRegisterController extends ImageControll{
	private AdminService adminService;
	private AdminRegisterValidator registerValidator;
	
	@Autowired
	public void init(AdminService adminService, AdminRegisterValidator registerValidator) {
		this.adminService = adminService;
		this.registerValidator = registerValidator;
	}
	
	
	@RequestMapping(method=RequestMethod.GET)
	public String showform(ModelMap model) {
		model.addAttribute(new Admin());
		return "admin/register";
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public String register(@ModelAttribute @Valid Admin admin, BindingResult result, SessionStatus status, HttpServletRequest request) throws Exception {
		this.registerValidator.validate(admin, result);
		if (result.hasErrors()) {
			return "admin/register";
		}
		else {
			this.adminService.add(admin);				
			status.setComplete();
			return "redirect:../welcome";
		}
	}
}
