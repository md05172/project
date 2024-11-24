package kr.ac.kopo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.ac.kopo.dao.OrdersDao;
import kr.ac.kopo.model.Address;
import kr.ac.kopo.model.Customer;
import kr.ac.kopo.model.Orders;
import kr.ac.kopo.model.OrdersDetail;

@Service
public class OrdersServiceImpl implements OrdersService{
	
	@Autowired
	OrdersDao dao;

	@Override
	public Address check(Address address) {
		return dao.check(address);
	}
	
	@Override
	public void address(Customer customer) {
		dao.address(customer);
	}

	@Override
	@Transactional
	public void add(Orders orders) {
		dao.add(orders);
		
		for(OrdersDetail detail : orders.getDetails()) {
			detail.setOrdersId(orders.getId());
			
			dao.addDetail(detail);
		}
		
	}

	@Override
	public boolean orderCheck(String orderId) {
		return dao.orderCheck(orderId);
	}

}
