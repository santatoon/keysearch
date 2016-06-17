package santatoon.wand.web.validator;

import javax.inject.Inject;
import javax.inject.Provider;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import santatoon.wand.domain.Admin;
import santatoon.wand.domain.Customer;
import santatoon.wand.domain.Login;
import santatoon.wand.service.AdminService;
import santatoon.wand.service.CustomerService;
import santatoon.wand.web.security.LoginInfo;

@Component
public class LoginValidator implements Validator{
	private CustomerService customerService;
	
	@Inject
	private Provider<LoginInfo> loginInfoProvider;

	@Autowired
	public void setUserService(CustomerService customerService) {
		this.customerService = customerService;
	}
	
	public boolean supports(Class<?> clazz) {
		return Login.class.isAssignableFrom(clazz);
	}
	
	public void validate(Object target, Errors errors) {
		Login login = (Login)target;
		Customer customer = customerService.get(login.getEmail());
		if (customer == null || !customer.getPassword().equals(login.getPassword())) {
			errors.reject("invalidLogin", "Invalid Login");
		}
		else {
			LoginInfo loginInfo = loginInfoProvider.get();
			loginInfo.save(customer);
		}
	}
}
