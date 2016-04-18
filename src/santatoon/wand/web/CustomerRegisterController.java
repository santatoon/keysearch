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

import santatoon.wand.domain.Customer;
import santatoon.wand.service.CustomerService;

@Controller
@Transactional
@RequestMapping("/customers/register")
@SessionAttributes("customer")
public class CustomerRegisterController{
	private CustomerService customerService;
	
	@Autowired
	public void init(CustomerService customerService) {
		this.customerService = customerService;
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public String showform(ModelMap model) {
		model.addAttribute(new Customer());
		return "customers/register";
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public String register(@ModelAttribute @Valid Customer customer, BindingResult result, SessionStatus status, HttpServletRequest request) throws Exception {
		if (result.hasErrors()) {
			return "customers/register";
		}
		else {
	
			int customerId = this.customerService.add(customer);	
			customer.setId(customerId);
			customer.initDates();
			this.customerService.update(customer);
			status.setComplete();
			return "redirect:list";
		}
	}
}
