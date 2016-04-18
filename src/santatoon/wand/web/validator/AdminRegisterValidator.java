package santatoon.wand.web.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import santatoon.wand.domain.Admin;
import santatoon.wand.domain.Customer;
import santatoon.wand.service.AdminService;

@Component
public class AdminRegisterValidator implements Validator {
	@Autowired
	private AdminService adminService;

	public boolean supports(Class<?> clazz) {
		return Customer.class.isAssignableFrom(clazz);
	}

	public void validate(Object target, Errors errors) {
		Admin formUser = (Admin) target;
		Admin user = this.adminService.get(formUser.getId());
		if (user != null && user.getId() != formUser.getId())
			errors.rejectValue("id", "duplicateId");
	}

}
