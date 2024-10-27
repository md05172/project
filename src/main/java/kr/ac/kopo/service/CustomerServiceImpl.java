package kr.ac.kopo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.ac.kopo.dao.CustomerDao;
import kr.ac.kopo.model.Customer;

@Service
public class CustomerServiceImpl implements CustomerService{

	@Autowired
	CustomerDao dao;
	
	@Override
	public void join(Customer item) {
		item.setRole(1);
		
		dao.join(item);
		
		Customer cust = check(item.getEmail());
		
		item.setId(cust.getId());
		
		dao.address(item);
	}

	@Override
	public Customer check(String email) {
		return dao.check(email);
	}

}
