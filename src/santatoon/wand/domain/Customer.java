package santatoon.wand.domain;

import java.sql.Date;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

public class Customer {
	@NotNull
	int id;

	@NotNull
	String email;
	
	@NotNull
	String password;

	@NotNull
	String firstname;
	
	@NotNull
	String lastname;

	@NotNull
	Skintype skintype;

	@NotNull
	Troubletype troubletype;

	@DateTimeFormat(style = "M-")
	Date created;
	
	@DateTimeFormat(style = "M-")
	Date modified;


	public Customer() {
	}
	public Customer(int id, String email, String password, String firstname, String lastname, Skintype skintype, Troubletype troubletype,
			Date created, Date modified) {
		super();
		this.id = id;
		this.email = email;
		this.password = password;
		this.firstname = firstname;
		this.lastname = lastname;
		this.skintype = skintype;
		this.troubletype = troubletype;
		this.created = created;
		this.modified = modified;
	}
	
	public int getId() {
		return id;
	}



	public void setId(int id) {
		this.id = id;
	}



	public String getEmail() {
		return email;
	}



	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirstname() {
		return firstname;
	}



	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}



	public String getLastname() {
		return lastname;
	}



	public void setLastname(String lastname) {
		this.lastname = lastname;
	}



	public Skintype getSkintype() {
		return skintype;
	}



	public void setSkintype(Skintype skintype) {
		this.skintype = skintype;
	}



	public Troubletype getTroubletype() {
		return troubletype;
	}



	public void setTroubletype(Troubletype troubletype) {
		this.troubletype = troubletype;
	}



	public Date getCreated() {
		return created;
	}



	public void setCreated(Date created) {
		this.created = created;
	}



	public Date getModified() {
		return modified;
	}



	public void setModified(Date modified) {
		this.modified = modified;
	}



	

	

	public void initDates() {
		Date now = new Date(new java.util.Date().getTime());
		if (this.created == null)
			this.created = now;
		if (this.modified == null)
			this.modified = now;
	}
}
