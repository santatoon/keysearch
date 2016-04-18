package santatoon.wand.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import santatoon.wand.dao.AdminDao;
import santatoon.wand.domain.Admin;

@Service
@Transactional
public class AdminServiceImpl implements AdminService{

	private AdminDao adminDao;
	
	@Autowired
	public void setEmployeeDao(AdminDao adminDao) {
		this.adminDao = adminDao;
	}

	@Override
	public void add(Admin admin) {
		// TODO Auto-generated method stub
		this.adminDao.add(admin);
	}

	@Override
	public void update(Admin admin) {
		// TODO Auto-generated method stub
		this.adminDao.update(admin);
	}

	@Override
	public void delete(String id) {
		// TODO Auto-generated method stub
		this.adminDao.delete(id);
	}

	@Override
	public Admin get(String id) {
		// TODO Auto-generated method stub
		return this.adminDao.get(id);
	}

	@Override
	public List<Admin> getAll() {
		// TODO Auto-generated method stub
		return this.adminDao.getAll();
	}
	
}
