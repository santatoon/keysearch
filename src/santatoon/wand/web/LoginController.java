package santatoon.wand.web;

import javax.inject.Inject;
import javax.inject.Provider;
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

import santatoon.wand.domain.Login;
import santatoon.wand.web.security.LoginInfo;
import santatoon.wand.web.validator.LoginValidator;


@Controller
@Transactional
@RequestMapping("/login")
@SessionAttributes("login") 
public class LoginController {
	private LoginValidator loginValidator;
	private Provider<LoginInfo> loginInfoProvider;

	@Inject 	
	public void setLoginInfoProvider(Provider<LoginInfo> loginInfoProvider) {
		this.loginInfoProvider = loginInfoProvider;
	}

	@Autowired
	public void init(LoginValidator loginValidator) {
		this.loginValidator = loginValidator;
	}

	@RequestMapping(method=RequestMethod.GET)
	public String showform(ModelMap model) {
		model.addAttribute(new Login());
		return "login";
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public String login(@ModelAttribute @Valid Login login, BindingResult result, SessionStatus status) {
		if (result.hasErrors()) return "login";
		
		this.loginValidator.validate(login, result);
		if (result.hasErrors()) {
			return "login";
		}
		else {
			status.setComplete();
			return "redirect:customers/list";
		}
	}
}
