package santatoon.wand.domain;

import java.sql.Date;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

public class Customer {
	
	int id;


	@NotNull
	@Size(min = 2, max = 4)
	String name;

	String phone;

	String ref;

	@DateTimeFormat(style = "M-")
	Date created;
	
	@DateTimeFormat(style = "M-")
	Date modified;


	public Customer() {
	}

	public Customer(String name, String phone, String ref) {
		super();
		this.name = name;
		this.phone = phone;
		this.ref = ref;

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public String getRef() {
		return ref;
	}

	public void setRef(String ref) {
		this.ref = ref;
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
