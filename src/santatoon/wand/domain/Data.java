package santatoon.wand.domain;

import java.util.ArrayList;
import java.util.List;

public class Data {
	public List<String> tags;
	public String type;
	public String link;
	public Images images;
	public Videos videos;
	public Caption caption;
	public User user;

	public Data() {
		this.tags = new ArrayList<String>();
		images = new Images();
		videos = new Videos();
		caption = new Caption();
		user = new User();
	}

	public Data(List<String> tags, String type, String link, Images images, Videos videos, Caption caption, User user) {
		super();
		this.tags = tags;
		this.type = type;
		this.link = link;
		this.images = images;
		this.videos = videos;
		this.caption = caption;
		this.user = user;
	}

	public List<String> getTags() {
		return this.tags;
	}

	public void setTags(List<String> tags) {
		this.tags = tags;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getLink() {
		return this.link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public Images getImages() {
		return this.images;
	}

	public void setImages(Images images) {
		this.images = images;
	}

	public Videos getVideos() {
		return this.videos;
	}

	public void setVideos(Videos videos) {
		this.videos = videos;
	}

	public Caption getCaption() {
		return this.caption;
	}

	public void setCaption(Caption caption) {
		this.caption = caption;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	
	public class Images {
		public Resolution low_resolution;
		public Resolution thumbnail;
		public Resolution standard_resolution;

		public Images() {
			low_resolution = new Resolution();
			thumbnail = new Resolution();
			standard_resolution = new Resolution();
		}

		public Images(Resolution low_resolution, Resolution thumbnail, Resolution standard_resolution) {
			super();
			this.low_resolution = low_resolution;
			this.thumbnail = thumbnail;
			this.standard_resolution = standard_resolution;
		}

		public Resolution getLow_resolution() {
			return low_resolution;
		}

		public void setLow_resolution(Resolution low_resolution) {
			this.low_resolution = low_resolution;
		}

		public Resolution getThumbnail() {
			return thumbnail;
		}

		public void setThumbnail(Resolution thumbnail) {
			this.thumbnail = thumbnail;
		}

		public Resolution getStandard_resolution() {
			return standard_resolution;
		}

		public void setStandard_resolution(Resolution standard_resolution) {
			this.standard_resolution = standard_resolution;
		}

	}

	public class Videos {
		public Resolution low_resolution;
		public Resolution standard_resolution;
		public Resolution low_bandwidth;

		public Videos() {
			low_resolution = new Resolution();
			standard_resolution = new Resolution();
			low_bandwidth = new Resolution();
		}

		public Videos(Resolution low_resolution, Resolution standard_resolution, Resolution low_bandwidth) {
			super();
			this.low_resolution = low_resolution;
			this.standard_resolution = standard_resolution;
			this.low_bandwidth = low_bandwidth;
		}

		public Resolution getLow_resolution() {
			return low_resolution;
		}

		public void setLow_resolution(Resolution low_resolution) {
			this.low_resolution = low_resolution;
		}

		public Resolution getStandard_resolution() {
			return standard_resolution;
		}

		public void setStandard_resolution(Resolution standard_resolution) {
			this.standard_resolution = standard_resolution;
		}

		public Resolution getLow_bandwidth() {
			return low_bandwidth;
		}

		public void setLow_bandwidth(Resolution low_bandwidth) {
			this.low_bandwidth = low_bandwidth;
		}

	}

	public class Resolution {
		public String url;
		public int width;
		public int height;

		public Resolution() {

		}

		public Resolution(String url, int width, int height) {
			super();
			this.url = url;
			this.width = width;
			this.height = height;
		}

		public String getUrl() {
			return url;
		}

		public void setUrl(String url) {
			this.url = url;
		}

		public int getWidth() {
			return width;
		}

		public void setWidth(int width) {
			this.width = width;
		}

		public int getHeight() {
			return height;
		}

		public void setHeight(int height) {
			this.height = height;
		}

	}

	public class User {
		public String username;
		public String profile_picture;
		public String id;
		public String full_name;

		public User() {

		}

		public User(String username, String profile_picture, String id, String full_name) {
			super();
			this.username = username;
			this.profile_picture = profile_picture;
			this.id = id;
			this.full_name = full_name;
		}

		public String getUsername() {
			return username;
		}

		public void setUsername(String username) {
			this.username = username;
		}

		public String getProfile_picture() {
			return profile_picture;
		}

		public void setProfile_picture(String profile_picture) {
			this.profile_picture = profile_picture;
		}

		public String getId() {
			return id;
		}

		public void setId(String id) {
			this.id = id;
		}

		public String getFull_name() {
			return full_name;
		}

		public void setFull_name(String full_name) {
			this.full_name = full_name;
		}
		
	}

	public class Caption {
		public String created_time;
		public String text;
		public User from;
		public String id;

		public Caption() {
			from = new User();
		}

		public Caption(String created_time, String text, User from, String id) {
			super();
			this.created_time = created_time;
			this.text = text;
			this.from = from;
			this.id = id;
		}

		public String getCreated_time() {
			return created_time;
		}

		public void setCreated_time(String created_time) {
			this.created_time = created_time;
		}

		public String getText() {
			return text;
		}

		public void setText(String text) {
			this.text = text;
		}

		public User getFrom() {
			return from;
		}

		public void setFrom(User from) {
			this.from = from;
		}

		public String getId() {
			return id;
		}

		public void setId(String id) {
			this.id = id;
		}

	}

}


