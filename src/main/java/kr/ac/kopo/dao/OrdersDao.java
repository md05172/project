package kr.ac.kopo.dao;

import java.util.List;

import kr.ac.kopo.model.Address;
import kr.ac.kopo.model.Customer;
import kr.ac.kopo.model.Orders;
import kr.ac.kopo.model.OrdersDetail;

public interface OrdersDao {

	Address check(Address address);
	
	void address(Customer item);

	void add(Orders orders);

	void addDetail(OrdersDetail detail);

	boolean orderCheck(String orderId);

	List<Orders> list(Long customerId);
}
