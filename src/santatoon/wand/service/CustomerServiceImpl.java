package santatoon.wand.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import santatoon.wand.dao.CustomerDao;
import santatoon.wand.domain.Customer;


@Service
@Transactional
public class CustomerServiceImpl implements CustomerService {
	private CustomerDao customerDao;
	
	@Autowired
	public void setcustomerDao(CustomerDao customerDao) {
		this.customerDao = customerDao;
	}

	@Override
	public int add(Customer customer) {
		// TODO Auto-generated method stub
		customer.initDates();
		return this.customerDao.add(customer);
	}

	@Override
	public void update(Customer customer) {
		// TODO Auto-generated method stub
		this.customerDao.update(customer);
	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
		this.customerDao.delete(id);
	}

	@Override
	public Customer get(String email) {
		// TODO Auto-generated method stub
		return this.customerDao.get(email);
	}

	@Override
	public List<Customer> getAll() {
		// TODO Auto-generated method stub
		return this.customerDao.getAll();
	}

}
