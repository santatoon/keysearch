package santatoon.wand.domain;

import javax.validation.constraints.Size;

public class Login {
	@Size(min=2, max=16)
	String id;
	@Size(min=4, max=16)
	String password;

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
}
