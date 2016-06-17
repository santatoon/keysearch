package santatoon.wand.web;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLEncoder;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

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
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
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

import santatoon.wand.domain.Scrapbook;
import santatoon.wand.domain.Search;
import santatoon.wand.domain.Customer;
import santatoon.wand.domain.Data;
import santatoon.wand.service.ScrapbookService;
import santatoon.wand.web.SearchController.Response;
import santatoon.wand.web.security.LoginInfo;
import santatoon.wand.web.validator.AdminRegisterValidator;

@Controller
@Transactional
@RequestMapping("search/{query1}/detail/{query2}")
@SessionAttributes("result")
public class DetailController {
	
	private ScrapbookService scrapbookService;
	private AdminRegisterValidator registerValidator;
	private @Inject Provider<LoginInfo> loginInfoProvider;

	@Autowired
	public void init(ScrapbookService scrapbookService, AdminRegisterValidator registerValidator) {
		this.scrapbookService = scrapbookService;
		this.registerValidator = registerValidator;
	}
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.setDisallowedFields("id", "logins");
	}
	
	@ModelAttribute("currentUser")
	public Customer currentUser() {
		return loginInfoProvider.get().currentUser();
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

	private List<Data> instagram(String query1, String query2) throws Exception {
		List<Data> instaresult = new ArrayList<Data>();
		boolean check = false;
		String json = readUrl("https://api.instagram.com/v1/tags/" + query1
				+ "/media/recent?access_token=1631861081.3a81a9f.9d7b2e2bc94f42df935055677efb2c4d");

		Gson gson = new Gson();
		Response response = gson.fromJson(json, Response.class);

		dataloop: for (Data content : response.data) {
			for (String tag : content.getTags()) {
				if (tag.contains(query2)) {
					instaresult.add(content);
					continue dataloop;
				}
			}
			if (content.getCaption().getText().contains(query2)) {
				instaresult.add(content);
				continue dataloop;
			}
		}
		return instaresult;
	}
	
	private List<Data> naver(String query1, String query2) throws Exception
	{
		List<Data> naverresult = new ArrayList<Data>();
		int maxnum = 10;
		int startnum = 1;
		int display = 100;
		String clientId = "lfcDWKmjg9Wl4lxfZrRc";
		String clientSecret = "H3PnKOJZz8";
		String blog_url = "https://openapi.naver.com/v1/search/blog.xml";
		HttpClient client;
		HttpGet request;
		HttpResponse response;
		HttpEntity r_entity;
		String xmlString;
		DocumentBuilderFactory factory;
		DocumentBuilder db;
		InputSource inStream;
		Document doc;
		String query;
		
		while (naverresult.size() < maxnum) {
			if (maxnum - naverresult.size() < 100)
				display = maxnum - naverresult.size();

			query = "?query="+query1 + "%20" + query2 +"&display=" + display + "&start=" + startnum + "&sort=sim";
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
			// playcount = "empty";
			
			NodeList nl = doc.getElementsByTagName("item");
			for (int i = 1; i < nl.getLength(); i++) {
				if (nl.item(i).getNodeType() == org.w3c.dom.Node.ELEMENT_NODE) {
					org.w3c.dom.Element nameElement = (org.w3c.dom.Element) nl.item(i);
					Data tempdata = new Data();
					tempdata.setType(nameElement.getElementsByTagName("title").item(0).getTextContent() + "     by " + nameElement.getElementsByTagName("bloggername").item(0).getTextContent());
					tempdata.setLink(nameElement.getElementsByTagName("link").item(0).getTextContent());
					naverresult.add(tempdata);
				}
			}
			if (startnum - 1 == naverresult.size())
				break;
			else
				startnum = naverresult.size() + 1;
		}
		
		
		return naverresult;
	}

	@RequestMapping(method = RequestMethod.GET)
	public String detail_get(@PathVariable String query1, @PathVariable String query2, Model model,
			SessionStatus status, HttpSession session) throws Exception {
		List<String> templist = new ArrayList<String>();
		templist.add(query1);
		templist.add(query2);
		List<Data> instaresult = new ArrayList<Data>();
		List<Data> naverresult = new ArrayList<Data>();
		instaresult = instagram(query1, query2);
		naverresult = naver(query1, query2);
		model.addAttribute("filterlist", templist);
		model.addAttribute("instaresult", instaresult);
		model.addAttribute("naverresult", naverresult);
		model.addAttribute("search", new Search());
		model.addAttribute("scrapbook", new Scrapbook());
		return "detail";
	}

	@RequestMapping(method = RequestMethod.POST)
	public String detail_post(@ModelAttribute @Valid Search search, BindingResult result,
			@ModelAttribute @Valid Scrapbook scrapbook, BindingResult resultScrapbook, SessionStatus status)
			throws UnsupportedEncodingException {
		if(scrapbook.getLink()!=null) {
			if(loginInfoProvider.get().currentUser() != null){
				Date now = new Date(new java.util.Date().getTime());
				scrapbook.setCreated(now);
				scrapbook.setModified(now);
				scrapbook.setCustomerid(loginInfoProvider.get().currentUser().getId());
				scrapbook.setTags(URLEncoder.encode(scrapbook.getTags(), "UTF-8"));
				scrapbook.setCaption(URLEncoder.encode(scrapbook.getCaption(), "UTF-8"));
				this.scrapbookService.add(scrapbook);
				status.setComplete();
				return "redirect:../../../myscrapbook";
			}
			else {
				return "redirect:../../../signin";
			}
		}
		else {
			status.setComplete();
			return "redirect:../../" + URLEncoder.encode(search.getQuery(), "UTF-8");
		}
	}
}
