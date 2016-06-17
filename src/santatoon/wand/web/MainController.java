package santatoon.wand.web;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import javax.inject.Inject;
import javax.inject.Provider;
import javax.validation.Valid;

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
import santatoon.wand.domain.Search;
import santatoon.wand.web.security.LoginInfo;

@Controller
@Transactional
@SessionAttributes("search")
public class MainController {
	private @Inject Provider<LoginInfo> loginInfoProvider;

	@RequestMapping("/signin")
	public void sign() {
	}
	
	@RequestMapping("/test")
	public void test() {
	}
	
	@ModelAttribute("currentUser")
	public Customer currentUser() {
		return loginInfoProvider.get().currentUser();
	}
	
	@RequestMapping(value="/index", method=RequestMethod.GET)
	public String showSearchForm(ModelMap model) {
		model.addAttribute(new Search());
		return "index";
	}
	
	@RequestMapping(value="/index", method=RequestMethod.POST)
	public String clickSearch(@ModelAttribute @Valid Search search, BindingResult result, SessionStatus status) throws UnsupportedEncodingException
	{
		status.setComplete();
		return "redirect:search/"+URLEncoder.encode(search.getQuery(), "UTF-8");
	}

	@RequestMapping("/logout")
	public String logout() {
		loginInfoProvider.get().remove();
		return "redirect:signin";
	}

	@RequestMapping("/accessdenied")
	public String notlogin() {
		return "/accessdenied";
	}

}
