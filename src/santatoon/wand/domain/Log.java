package santatoon.wand.domain;


import java.sql.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class Log {
	int id;
	int customerid;
	int deviceid;
	Status status;
	String ref;
	@DateTimeFormat(style = "M-")
	Date operated;
	
	public Log() {
	}

	public Log(int id, int customerid, int deviceid, Status status, String ref) {
		super();
		this.id = id;
		this.customerid = customerid;
		this.deviceid = deviceid;
		this.status = status;
		this.ref = ref;
		this.operated = new Date(new java.util.Date().getTime());
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public int getCustomerid() {
		return customerid;
	}

	public void setCustomerid(int customerid) {
		this.customerid = customerid;
	}
	
	public int getDeviceid() {
		return deviceid;
	}

	public void setDeviceid(int deviceid) {
		this.deviceid = deviceid;
	}
	
	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}
	
	public String getRef() {
		return ref;
	}

	public void setRef(String ref) {
		this.ref = ref;
	}
	
	public Date getOperated() {
		return operated;
	}

	public void setOperated(Date operated) {
		this.operated = operated;
	}
	
	public void initDates() {
		if (this.operated == null)
			this.operated = new Date(new java.util.Date().getTime());
	}
}
