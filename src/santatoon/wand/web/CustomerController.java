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
import santatoon.wand.service.CustomerService;
import santatoon.wand.web.security.LoginInfo;

@Controller
@Transactional
@RequestMapping("/customers")
public class CustomerController {
	@Autowired
	private CustomerService customerService;
	@Inject
	private Provider<LoginInfo> loginInfoProvider;

	@ModelAttribute("currentUser")
	public Admin currentUser() {
		return loginInfoProvider.get().currentUser();
	}

	@RequestMapping("/list")
	public String list(ModelMap model) {
		model.addAttribute(this.customerService.getAll());
		return "customers/list";
	}

	@RequestMapping("/view/{id}")
	public String list(@PathVariable int id, ModelMap model) {
		model.addAttribute(this.customerService.get(id));
		return "customers/view";
	}

	@RequestMapping("/delete/{id}")
	public String delete(@PathVariable int id, HttpServletRequest request) {

		this.customerService.delete(id);
		return "customers/deleted";
	}
}
