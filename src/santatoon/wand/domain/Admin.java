package santatoon.wand.domain;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class Admin {
	@NotNull
	@Size(min = 4, max = 20)
	String id;

	@NotNull
	@Size(min = 4, max = 20)
	String password;

	@NotNull
	@Size(min = 2, max = 20)
	String name;

	@Size(min=4, max=20)
	String phone;

	@Size(min=4, max=40)
	String email;

	public Admin() {
	}

	public Admin(String id, String password, String name, String phone, String email) {
		super();
		this.id = id;
		this.password = password;
		this.name = name;
		this.phone = phone;
		this.email = email;
	}

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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}
