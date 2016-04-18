package santatoon.wand.web;

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

import santatoon.wand.domain.Customer;
import santatoon.wand.service.CustomerService;
import santatoon.wand.web.security.LoginInfo;
import santatoon.wand.web.validator.AdminRegisterValidator;

@Controller
@Transactional
@RequestMapping("/customers/edit/{id}")
@SessionAttributes("customer")
public class CustomerEditController {
	private CustomerService customerService;
	private AdminRegisterValidator registerValidator;
	private @Inject Provider<LoginInfo> loginInfoProvider;

	@Autowired
	public void init(CustomerService customerService, AdminRegisterValidator registerValidator) {
		this.customerService = customerService;
		this.registerValidator = registerValidator;
	}

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.setDisallowedFields("id", "logins");
	}

	@RequestMapping(method = RequestMethod.GET)
	public String showform(@PathVariable int id, ModelMap model) {
		model.addAttribute(this.customerService.get(id));
		return "customers/edit";
	}

	@RequestMapping(method = RequestMethod.POST)
	public String edit(@ModelAttribute @Valid Customer customer, BindingResult result, SessionStatus status,
			HttpServletRequest request) throws Exception {
		// this.registerValidator.validate(customer, result);
		if (result.hasErrors()) {
			return "customers/edit";
		} else {
			Date now = new Date(new java.util.Date().getTime());
			customer.setModified(now);
			this.customerService.update(customer);
			status.setComplete();
			return "redirect:../list";
		}
	}
}
