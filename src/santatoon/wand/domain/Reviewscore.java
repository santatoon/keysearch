package santatoon.wand.domain;

import java.sql.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class Reviewscore {

	int id;
	String word;
	int score;
	@DateTimeFormat(style = "M-")
	Date created;
	@DateTimeFormat(style = "M-")
	Date modified;

	public Reviewscore() {
	}

	public Reviewscore(int id, String word, int score, Date created, Date modified) {
		super();
		this.id = id;
		this.word = word;
		this.score = score;
		this.created = created;
		this.modified = modified;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getWord() {
		return word;
	}

	public void setWord(String word) {
		this.word = word;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
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
