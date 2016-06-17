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
import santatoon.wand.service.ScrapbookService;
import santatoon.wand.web.security.LoginInfo;
import santatoon.wand.web.validator.AdminRegisterValidator;

@Controller
@Transactional
@RequestMapping("/myscrapbook")
@SessionAttributes("scrapbook")
public class ScrapbookController {
	private ScrapbookService scrapbookService;
	private AdminRegisterValidator registerValidator;
	private @Inject Provider<LoginInfo> loginInfoProvider;

	@Autowired
	public void init(ScrapbookService scrapbookService, AdminRegisterValidator registerValidator) {
		this.scrapbookService = scrapbookService;
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

	@RequestMapping(method = RequestMethod.GET)
	public String showform(ModelMap model) {
		model.addAttribute("scrapbookList", this.scrapbookService.getAll(this.loginInfoProvider.get().currentUser().getId()));
		return "myscrapbook";
	}
}
