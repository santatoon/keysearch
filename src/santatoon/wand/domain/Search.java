package santatoon.wand.domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Search {
	String query;
	String secondquery;
	Map<String, Integer> occurrences;
	List<Data> instagramdata;

	

	public Search() {
		occurrences = new HashMap<String, Integer>();
		instagramdata = new ArrayList<Data>();
	}

	public Search(String query, String secondquery, Map<String, Integer> occurrences, List<Data> instagramdata) {
		super();
		this.query = query;
		this.secondquery = secondquery;
		this.occurrences = occurrences;
		this.instagramdata = instagramdata;
	}

	public String getQuery() {
		return query;
	}

	public void setQuery(String query) {
		this.query = query;
	}

	public String getSecondquery() {
		return secondquery;
	}

	public void setSecondquery(String secondquery) {
		this.secondquery = secondquery;
	}

	public Map<String, Integer> getOccurrences() {
		return occurrences;
	}

	public void setOccurrences(Map<String, Integer> occurrences) {
		this.occurrences = occurrences;
	}

	public List<Data> getInstagramdata() {
		return instagramdata;
	}

	public void setInstagramdata(List<Data> instagramdata) {
		this.instagramdata = instagramdata;
	}

}
