package santatoon.wand.domain;

import java.sql.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class Device {
	int id;

	String pushid;

	int customerid;
	Status status;

	@DateTimeFormat(style = "M-")
	Date created;
	
	@DateTimeFormat(style = "M-")
	Date modified;

	public Device() {
	}

	public Device(String pushid, int customerid, Status status) {
		super();
		this.pushid = pushid;
		this.customerid = customerid;
		this.status = status;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPushid() {
		return pushid;
	}

	public void setPushid(String pushid) {
		this.pushid = pushid;
	}

	public int getCustomerid() {
		return customerid;
	}

	public void setCustomerid(int customerid) {
		this.customerid = customerid;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
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
