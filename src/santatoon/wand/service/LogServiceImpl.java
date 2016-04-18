package santatoon.wand.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import santatoon.wand.dao.LogDao;
import santatoon.wand.domain.Log;

@Service
@Transactional
public class LogServiceImpl implements LogService {
	private LogDao logDao;
	
	@Autowired
	public void setlogDao(LogDao logDao) {
		this.logDao = logDao;
	}

	@Override
	public int add(Log log) {
		// TODO Auto-generated method stub
		log.initDates();
		return this.logDao.add(log);
	}

	@Override
	public void update(Log log) {
		// TODO Auto-generated method stub
		this.logDao.update(log);
	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
		this.logDao.delete(id);
	}

	@Override
	public Log get(int id) {
		// TODO Auto-generated method stub
		return this.logDao.get(id);
	}

	@Override
	public List<Log> getAll() {
		// TODO Auto-generated method stub
		return this.logDao.getAll();
	}

}