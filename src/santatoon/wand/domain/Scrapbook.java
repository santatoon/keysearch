package santatoon.wand.domain;

import java.sql.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class Scrapbook {

	int id;
	int customerid;
	String link;
	String tags;
	String caption;
	String imageurl;
	@DateTimeFormat(style = "M-")
	Date created;
	@DateTimeFormat(style = "M-")
	Date modified;

	public Scrapbook() {
	}

	public Scrapbook(int id, int customerid, String link, String tags, String caption, String imageurl, Date created,
			Date modified) {
		super();
		this.id = id;
		this.customerid = customerid;
		this.link = link;
		this.tags = tags;
		this.caption = caption;
		this.imageurl = imageurl;
		this.created = created;
		this.modified = modified;
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

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public String getTags() {
		return tags;
	}

	public void setTags(String tags) {
		this.tags = tags;
	}

	public String getCaption() {
		return caption;
	}

	public void setCaption(String caption) {
		this.caption = caption;
	}

	public String getImageurl() {
		return imageurl;
	}

	public void setImageurl(String imageurl) {
		this.imageurl = imageurl;
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
