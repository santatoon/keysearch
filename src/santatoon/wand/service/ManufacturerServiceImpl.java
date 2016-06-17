package santatoon.wand.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import santatoon.wand.dao.ManufacturerDao;
import santatoon.wand.domain.Manufacturer;


@Service
@Transactional
public class ManufacturerServiceImpl implements ManufacturerService {
	private ManufacturerDao manufacturerDao;
	
	@Autowired
	public void setmanufacturerDao(ManufacturerDao manufacturerDao) {
		this.manufacturerDao = manufacturerDao;
	}

	@Override
	public int add(Manufacturer manufacturer) {
		// TODO Auto-generated method stub
		manufacturer.initDates();
		return this.manufacturerDao.add(manufacturer);
	}

	@Override
	public void update(Manufacturer manufacturer) {
		// TODO Auto-generated method stub
		this.manufacturerDao.update(manufacturer);
	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
		this.manufacturerDao.delete(id);
	}

	@Override
	public Manufacturer get(String name) {
		// TODO Auto-generated method stub
		return this.manufacturerDao.get(name);
	}

	@Override
	public List<Manufacturer> getAll() {
		// TODO Auto-generated method stub
		return this.manufacturerDao.getAll();
	}

}
