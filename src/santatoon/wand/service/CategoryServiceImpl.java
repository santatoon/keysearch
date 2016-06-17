package santatoon.wand.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import santatoon.wand.dao.CategoryDao;
import santatoon.wand.domain.Category;


@Service
@Transactional
public class CategoryServiceImpl implements CategoryService {
	private CategoryDao categoryDao;
	
	@Autowired
	public void setcategoryDao(CategoryDao categoryDao) {
		this.categoryDao = categoryDao;
	}

	@Override
	public int add(Category category) {
		// TODO Auto-generated method stub
		category.initDates();
		return this.categoryDao.add(category);
	}

	@Override
	public void update(Category category) {
		// TODO Auto-generated method stub
		this.categoryDao.update(category);
	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
		this.categoryDao.delete(id);
	}

	@Override
	public Category get(String name) {
		// TODO Auto-generated method stub
		return this.categoryDao.get(name);
	}

	@Override
	public List<Category> getAll() {
		// TODO Auto-generated method stub
		return this.categoryDao.getAll();
	}

}
