package santatoon.wand.web.validator;

import javax.inject.Inject;
import javax.inject.Provider;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import santatoon.wand.domain.Admin;
import santatoon.wand.domain.Login;
import santatoon.wand.service.AdminService;
import santatoon.wand.web.security.LoginInfo;

@Component
public class LoginValidator implements Validator{
	private AdminService adminService;
	
	@Inject
	private Provider<LoginInfo> loginInfoProvider;

	@Autowired
	public void setUserService(AdminService adminService) {
		this.adminService = adminService;
	}
	
	public boolean supports(Class<?> clazz) {
		return Login.class.isAssignableFrom(clazz);
	}
	
	public void validate(Object target, Errors errors) {
		Login login = (Login)target;
		Admin admin = adminService.get(login.getId());
		if (admin == null || !admin.getPassword().equals(login.getPassword())) {
			errors.reject("invalidLogin", "Invalid Login");
		}
		else {
			LoginInfo loginInfo = loginInfoProvider.get();
			loginInfo.save(admin);
		}
	}
}
