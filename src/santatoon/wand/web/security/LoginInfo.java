package santatoon.wand.web.security;

import java.util.Date;

import santatoon.wand.domain.Admin;

public interface LoginInfo {
	void save(Admin user);
	void remove();
	boolean isLoggedIn();
	Admin currentUser();
	Date getLoginTime();
}
