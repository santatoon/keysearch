package santatoon.wand.domain;

import java.sql.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class Category {

	int id;
	String name;
	@DateTimeFormat(style = "M-")
	Date created;
	@DateTimeFormat(style = "M-")
	Date modified;

	public Category() {
	}

	public Category(int id, String name, Date created, Date modified) {
		super();
		this.id = id;
		this.name = name;
		this.created = created;
		this.modified = modified;
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
