package santatoon.wand.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import santatoon.wand.dao.ScrapbookDao;
import santatoon.wand.domain.Scrapbook;


@Service
@Transactional
public class ScrapbookServiceImpl implements ScrapbookService {
	private ScrapbookDao scrapbookDao;
	
	@Autowired
	public void setscrapbookDao(ScrapbookDao scrapbookDao) {
		this.scrapbookDao = scrapbookDao;
	}

	@Override
	public int add(Scrapbook scrapbook) {
		// TODO Auto-generated method stub
		scrapbook.initDates();
		return this.scrapbookDao.add(scrapbook);
	}

	@Override
	public void update(Scrapbook scrapbook) {
		// TODO Auto-generated method stub
		this.scrapbookDao.update(scrapbook);
	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
		this.scrapbookDao.delete(id);
	}

	@Override
	public Scrapbook get(int id) {
		// TODO Auto-generated method stub
		return this.scrapbookDao.get(id);
	}

	@Override
	public List<Scrapbook> getAll(int customerid) {
		// TODO Auto-generated method stub
		return this.scrapbookDao.getAll(customerid);
	}

}
