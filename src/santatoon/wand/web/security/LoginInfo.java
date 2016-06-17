package santatoon.wand.web.security;

import java.util.Date;

import santatoon.wand.domain.Customer;

public interface LoginInfo {
	void save(Customer user);
	void remove();
	boolean isLoggedIn();
	Customer currentUser();
	Date getLoginTime();
}
