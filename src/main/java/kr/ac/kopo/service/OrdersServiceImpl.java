package kr.ac.kopo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.ac.kopo.dao.OrdersDao;
import kr.ac.kopo.model.Customer;
import kr.ac.kopo.model.Orders;

@Service
public class OrdersServiceImpl implements OrdersService{
	
	@Autowired
	OrdersDao dao;

	@Override
	public Orders check(Customer customer) {
		return dao.check(customer);
	}
	
}
