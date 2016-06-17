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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import santatoon.wand.domain.Customer;
import santatoon.wand.domain.Skintype;
import santatoon.wand.domain.Troubletype;
import santatoon.wand.service.CustomerService;
import santatoon.wand.web.security.LoginInfo;
import santatoon.wand.web.validator.AdminRegisterValidator;

@Controller
@Transactional
@RequestMapping("/mypage")
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
	
	@ModelAttribute("currentUser")
	public Customer currentUser() {
		return loginInfoProvider.get().currentUser();
	}
	
	@ModelAttribute
	public List<Skintype> skintypes() {
		Skintype[] skintype = Skintype.values();
		return Arrays.asList(skintype);
	}
	
	@ModelAttribute
	public List<Troubletype> troubletypes() {
		Troubletype[] troubletype = Troubletype.values();
		return Arrays.asList(troubletype);
	}

	@RequestMapping(method = RequestMethod.GET)
	public String showform(ModelMap model) {
		model.addAttribute("customer", this.customerService.get(this.loginInfoProvider.get().currentUser().getEmail()));
		return "mypage";
	}

	@RequestMapping(method = RequestMethod.POST)
	public String edit(@ModelAttribute @Valid Customer customer, BindingResult result, SessionStatus status,
			HttpServletRequest request) throws Exception {
		// this.registerValidator.validate(customer, result);
		if (result.hasErrors()) {
			return "mypage";
		} else {
			Date now = new Date(new java.util.Date().getTime());
			customer.setModified(now);
			this.customerService.update(customer);
			status.setComplete();
			return "redirect:mypage";
		}
	}
}
