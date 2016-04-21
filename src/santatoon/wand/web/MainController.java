package santatoon.wand.web;

import java.util.ArrayList;

import javax.inject.Inject;
import javax.inject.Provider;

import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;

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
@RequestMapping
public class MainController {
	private @Inject Provider<LoginInfo> loginInfoProvider;

	@RequestMapping("/welcome")
	public void welcome() {
	}

	@RequestMapping("/search1")
	public void test() {
	}

	@RequestMapping("/logout")
	public String logout() {
		loginInfoProvider.get().remove();
		return "redirect:login";
	}

	@RequestMapping("/accessdenied")
	public String notlogin() {
		return "/accessdenied";
	}

	@RequestMapping("/search2")
	public void twitter() throws TwitterException {

		ConfigurationBuilder cb = new ConfigurationBuilder();
		cb.setDebugEnabled(true).setOAuthConsumerKey("TyF07njD1ZEhszBW9ZGw30O1R")
				.setOAuthConsumerSecret("GPnyJxnjexoiUek0xXT2YuGDlQYuiofJfrVxPxTe8nz29LnuEd")
				.setOAuthAccessToken("384270297-Myk7okiLzLP0xwoVGvSwUhP2eQr3clqjZLR0EsDy")
				.setOAuthAccessTokenSecret("zgS7B4Okzqk61MNJzhlF7vjVfQ1gv6MZvqbu4QNCFlPjj");

		TwitterFactory tf = new TwitterFactory(cb.build());
		Twitter twitter = tf.getInstance();
		Query query = new Query("#mlb");
		query.setCount(100);
		int numberOfMaxTweets = 1000;
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
			System.out.println("Gathered " + tweets.size() + " " + numberOfTweets);

			for (Status t : temptweets)
				if (t.getId() < lastID)
					lastID = t.getId();
			query.setMaxId(lastID - 1);
			temptweets.clear();
		}
	}

}
