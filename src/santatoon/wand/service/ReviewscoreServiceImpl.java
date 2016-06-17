package santatoon.wand.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import santatoon.wand.dao.ReviewscoreDao;
import santatoon.wand.domain.Reviewscore;


@Service
@Transactional
public class ReviewscoreServiceImpl implements ReviewscoreService {
	private ReviewscoreDao reviewscoreDao;
	
	@Autowired
	public void setreviewscoreDao(ReviewscoreDao reviewscoreDao) {
		this.reviewscoreDao = reviewscoreDao;
	}

	@Override
	public int add(Reviewscore reviewscore) {
		// TODO Auto-generated method stub
		reviewscore.initDates();
		return this.reviewscoreDao.add(reviewscore);
	}

	@Override
	public void update(Reviewscore reviewscore) {
		// TODO Auto-generated method stub
		this.reviewscoreDao.update(reviewscore);
	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
		this.reviewscoreDao.delete(id);
	}

	@Override
	public Reviewscore get(String name) {
		// TODO Auto-generated method stub
		return this.reviewscoreDao.get(name);
	}

	@Override
	public List<Reviewscore> getAll() {
		// TODO Auto-generated method stub
		return this.reviewscoreDao.getAll();
	}

}
