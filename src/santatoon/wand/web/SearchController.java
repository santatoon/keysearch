package santatoon.wand.web;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Provider;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;

import kr.co.shineware.nlp.komoran.core.Komoran;
import kr.co.shineware.nlp.komoran.model.KomoranResult;
import santatoon.wand.domain.Category;
import santatoon.wand.domain.Customer;
import santatoon.wand.domain.Reviewscore;
import santatoon.wand.domain.Search;
import santatoon.wand.domain.Data;
import santatoon.wand.service.CategoryService;
import santatoon.wand.service.ReviewscoreService;
import santatoon.wand.web.security.LoginInfo;
import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

@Controller
@Transactional
@RequestMapping("search/{query}")
@SessionAttributes("result")
public class SearchController {
	
	private ReviewscoreService reviewscoreService;
	private CategoryService categoryService;
	private @Inject Provider<LoginInfo> loginInfoProvider;

	List<String> WORDS;
	Map<String, Integer> occurrences;
	Komoran komoran;
	KomoranResult analyzeResultList;
	String keyword;
	
	Map<String, Integer> reviewscores;
	List<String> categories;
	List<String> products;
	List<String> manufacturers;
	public void initWords(){
		
	}
	@Autowired
	public void init(ReviewscoreService reviewscoreService, CategoryService categoryService) {
		this.reviewscoreService = reviewscoreService;
		this.categoryService = categoryService;
	}
	@ModelAttribute("currentUser")
	public Customer currentUser() {
		return loginInfoProvider.get().currentUser();
	}

	List<Data> instagram_data;

	class Response {
		@SerializedName("data")
		List<Data> data;
	}

	private static String readUrl(String urlString) throws Exception {
		BufferedReader reader = null;
		try {
			URL url = new URL(urlString);
			reader = new BufferedReader(new InputStreamReader(url.openStream()));
			StringBuffer buffer = new StringBuffer();
			int read;
			char[] chars = new char[1024];
			while ((read = reader.read(chars)) != -1)
				buffer.append(chars, 0, read);

			return buffer.toString();
		} finally {
			if (reader != null)
				reader.close();
		}
	}

	private void instagram() throws Exception {
		String json = readUrl("https://api.instagram.com/v1/tags/" + keyword
				+ "/media/recent?access_token=1631861081.3a81a9f.9d7b2e2bc94f42df935055677efb2c4d");

		Gson gson = new Gson();
		Response response = gson.fromJson(json, Response.class);
		instagram_data = response.data;
		for (Data content : response.data) {
			WORDS.addAll(content.tags);
			String input = content.caption.text;
			analyzeResultList = komoran.analyze(input);
			WORDS.addAll(analyzeResultList.getResult());
		}
	}

	private void naver() throws Exception {
		int maxnum = 200;
		int startnum = 1;
		int display = 100;
		String clientId = "lfcDWKmjg9Wl4lxfZrRc";
		String clientSecret = "H3PnKOJZz8";
		String query;
		String blog_url = "https://openapi.naver.com/v1/search/blog.xml";
		ArrayList<String> blog_list = new ArrayList<String>();
		HttpClient client;
		HttpGet request;
		HttpResponse response;
		HttpEntity r_entity;
		String xmlString;
		DocumentBuilderFactory factory;
		DocumentBuilder db;
		InputSource inStream;
		Document doc;
		while (blog_list.size() < maxnum) {
			if (maxnum - blog_list.size() < 100)
				display = maxnum - blog_list.size();

			query = "?query=" + keyword + "&display=" + display + "&start=" + startnum + "&sort=sim";
			client = HttpClientBuilder.create().build();
			request = new HttpGet(blog_url + query);

			request.addHeader("X-Naver-Client-Id", clientId);
			request.addHeader("X-Naver-Client-Secret", clientSecret);
			response = client.execute(request);

			r_entity = response.getEntity();
			xmlString = EntityUtils.toString(r_entity);

			factory = DocumentBuilderFactory.newInstance();
			db = factory.newDocumentBuilder();
			inStream = new InputSource();

			inStream.setCharacterStream(new StringReader(xmlString));
			doc = db.parse(inStream);

			NodeList nl = doc.getElementsByTagName("description");
			for (int i = 1; i < nl.getLength(); i++) {
				if (nl.item(i).getNodeType() == org.w3c.dom.Node.ELEMENT_NODE) {
					org.w3c.dom.Element nameElement = (org.w3c.dom.Element) nl.item(i);
					if (nameElement.getFirstChild() != null) {
						blog_list.add(nameElement.getFirstChild().getNodeValue().trim());
						analyzeResultList = komoran.analyze(nameElement.getFirstChild().getNodeValue().trim());
						WORDS.addAll(analyzeResultList.getResult());
					}
				}
			}
			if (startnum - 1 == blog_list.size())
				break;
			else
				startnum = blog_list.size() + 1;
		}
	}

	public static <K, V extends Comparable<? super V>> Map<K, V> sortByValue(Map<K, V> map) {
		List<Map.Entry<K, V>> list = new LinkedList<>(map.entrySet());
		Collections.sort(list, new Comparator<Map.Entry<K, V>>() {
			@Override
			public int compare(Map.Entry<K, V> o1, Map.Entry<K, V> o2) {
				return (o2.getValue()).compareTo(o1.getValue());
			}
		});

		Map<K, V> result = new LinkedHashMap<>();
		for (Map.Entry<K, V> entry : list) {
			result.put(entry.getKey(), entry.getValue());
		}
		return result;
	}

	public void twitter() throws TwitterException {

		ConfigurationBuilder cb = new ConfigurationBuilder();
		cb.setDebugEnabled(true).setOAuthConsumerKey("TyF07njD1ZEhszBW9ZGw30O1R")
				.setOAuthConsumerSecret("GPnyJxnjexoiUek0xXT2YuGDlQYuiofJfrVxPxTe8nz29LnuEd")
				.setOAuthAccessToken("384270297-Myk7okiLzLP0xwoVGvSwUhP2eQr3clqjZLR0EsDy")
				.setOAuthAccessTokenSecret("zgS7B4Okzqk61MNJzhlF7vjVfQ1gv6MZvqbu4QNCFlPjj");

		TwitterFactory tf = new TwitterFactory(cb.build());
		Twitter twitter = tf.getInstance();
		Query query = new Query(keyword);
		query.setCount(100);
		int numberOfMaxTweets = 100;
		int numberOfTweets = 0;
		long lastID = Long.MAX_VALUE;
		ArrayList<Status> tweets = new ArrayList<Status>();
		ArrayList<Status> temptweets = new ArrayList<Status>();
		while (tweets.size() < numberOfMaxTweets) {
			if (numberOfMaxTweets - tweets.size() < 100)
				query.setCount(numberOfMaxTweets - tweets.size());

			QueryResult result = twitter.search(query);
			temptweets.addAll(result.getTweets());
			tweets.addAll(temptweets);
			if (numberOfTweets == tweets.size())
				break;
			else
				numberOfTweets = tweets.size();

			for (Status t : temptweets)
				if (t.getId() < lastID)
					lastID = t.getId();
			query.setMaxId(lastID - 1);
			temptweets.clear();
		}

		for (Status t : tweets) {
			analyzeResultList = komoran.analyze(t.getText());
			WORDS.addAll(analyzeResultList.getResult());
		}
	}

	@RequestMapping(method = RequestMethod.GET)
	public String search_get(@PathVariable String query, Model model, HttpSession session) throws Exception {

		komoran = new Komoran("C:/Users/Piljoo/git/KOMORAN/models");
		komoran.setFWDic("C:/Users/Piljoo/git/KOMORAN/user_data/fwd.user");
		komoran.setUserDic("C:/Users/Piljoo/git/KOMORAN/user_data/dic.user");
		WORDS = new ArrayList<String>();
		occurrences = new HashMap<String, Integer>();
		keyword = query;

		instagram();
		// naver();
		// twitter();

		for (String temp : WORDS) {
			if (!temp.equals(keyword)) {
				int count;
				if (occurrences.get(temp) == null)
					count = 0;
				else
					count = occurrences.get(temp);
				occurrences.put(temp, count + 1);
			}
		}
		//occurrences = sortByValue(occurrences);
		List<Reviewscore> scores = reviewscoreService.getAll();
		List<Category> categories = categoryService.getAll();
		
		Map<String, Integer> topoccurrences = new HashMap<String, Integer>();
		
		Iterator<Map.Entry<String, Integer>> iter = occurrences.entrySet().iterator();
		boolean ischecked;
		while(iter.hasNext()){
			ischecked=false;
			Map.Entry<String, Integer> entry = iter.next();
			for(Reviewscore score: scores){
				if(entry.getKey().equals(score.getWord())){
					occurrences.put(entry.getKey(), entry.getValue()*score.getScore());
					ischecked=true;
					break;
				}
			}
			if(!ischecked){
				for(Category category: categories){
					if(entry.getKey().equals(category.getName())){
						iter.remove();
						//occurrences.remove(entry.getKey());
						break;
					}
				}
			}
		}
		
		
		int tempcount = 0;

		/*
		while(iter.hasNext()){
			Map.Entry<String, Integer> entry = iter.next();
			System.out.println(entry.getKey() + " " + entry.getValue());
			if ((++tempcount) > 20)
				break;
			else {
				topoccurrences.put(entry.getKey(), 20-tempcount);
			}
		}
		*/
		occurrences = sortByValue(occurrences);
		for (String word : occurrences.keySet()) {
			System.out.println(word + " " + occurrences.get(word));
			if ((++tempcount) > 20)
				break;
			else {
				topoccurrences.put(word, 20-tempcount);
			}
		}
		

		Search result = new Search();
		result.setQuery(query);
		result.setOccurrences(topoccurrences);
		model.addAttribute("search", new Search());
		model.addAttribute("result", result);
		return "search";
	}

	@RequestMapping(method = RequestMethod.POST)
	public String search_post(@ModelAttribute @Valid Search search, BindingResult resultSearch,
			@ModelAttribute @Valid Search result, BindingResult resultResult, SessionStatus status, HttpSession session) throws UnsupportedEncodingException {
		// Search sessionresult = (Search) session.getAttribute("result");
		// sessionresult.setQuery(result.getQuery());
		// sessionresult.setSecondquery(result.getSecondquery());
		if (resultSearch.hasErrors() || resultResult.hasErrors()) {
			return "search";
		} else {
			status.setComplete();
			if (result.getSecondquery() == null)
				return "redirect:" + URLEncoder.encode(search.getQuery(), "UTF-8");
			else {
				// redirectAttributes.addFlashAttribute("instaresult",
				// sessionresult);
				return "redirect:" + URLEncoder.encode(result.getQuery(), "UTF-8") + "/detail/"
						+ URLEncoder.encode(result.getSecondquery(), "UTF-8");
			}
		}
	}
}
