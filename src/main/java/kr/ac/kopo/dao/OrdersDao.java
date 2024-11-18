package kr.ac.kopo.dao;

import kr.ac.kopo.model.Customer;
import kr.ac.kopo.model.Orders;

public interface OrdersDao {

	Orders check(Customer customer);
	
}
