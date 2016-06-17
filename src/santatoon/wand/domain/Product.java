package santatoon.wand.domain;

import java.sql.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class Product {

	int id;
	int categoryid;
	int manufacturerid;
	String name;
	@DateTimeFormat(style = "M-")
	Date created;
	@DateTimeFormat(style = "M-")
	Date modified;

	public Product() {
	}
	public Product(int id, int categoryid, int manufacturerid, String name, Date created, Date modified) {
		super();
		this.id = id;
		this.categoryid = categoryid;
		this.manufacturerid = manufacturerid;
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
	public int getCategoryid() {
		return categoryid;
	}
	public void setCategoryid(int categoryid) {
		this.categoryid = categoryid;
	}
	public int getManufacturerid() {
		return manufacturerid;
	}
	public void setManufacturerid(int manufacturerid) {
		this.manufacturerid = manufacturerid;
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
