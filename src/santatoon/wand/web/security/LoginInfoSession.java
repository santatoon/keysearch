package santatoon.wand.web.security;

import java.util.Date;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import santatoon.wand.domain.Customer;

@Component
@Scope("session")
public class LoginInfoSession implements LoginInfo {
	private Customer currentUser;
	private Date loginTime;
	
	public Customer currentUser() {
		return this.currentUser;
	}

	public boolean isLoggedIn() {
		return (this.currentUser != null);
	}

	public void remove() {
		if (this.currentUser == null) throw new IllegalStateException();
		this.currentUser = null;
		this.loginTime = null;
	}

	public void save(Customer user) {
		this.currentUser = user;
		this.loginTime = new Date();
	}

	public Date getLoginTime() {
		return this.loginTime;
	}

}
