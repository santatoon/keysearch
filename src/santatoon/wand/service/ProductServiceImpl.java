package santatoon.wand.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import santatoon.wand.dao.ProductDao;
import santatoon.wand.domain.Product;


@Service
@Transactional
public class ProductServiceImpl implements ProductService {
	private ProductDao productDao;
	
	@Autowired
	public void setproductDao(ProductDao productDao) {
		this.productDao = productDao;
	}

	@Override
	public int add(Product product) {
		// TODO Auto-generated method stub
		product.initDates();
		return this.productDao.add(product);
	}

	@Override
	public void update(Product product) {
		// TODO Auto-generated method stub
		this.productDao.update(product);
	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
		this.productDao.delete(id);
	}

	@Override
	public Product get(String name) {
		// TODO Auto-generated method stub
		return this.productDao.get(name);
	}

	@Override
	public List<Product> getAll() {
		// TODO Auto-generated method stub
		return this.productDao.getAll();
	}

}
