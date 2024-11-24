package kr.ac.kopo.service;

import kr.ac.kopo.model.Address;
import kr.ac.kopo.model.Customer;
import kr.ac.kopo.model.Orders;

public interface OrdersService {
	
	// 주문테이블에서 고객과 주소를 비교해서 있는 주소인지 확인
	Address check(Address address);

	void address(Customer customer);

	void add(Orders orders);

	boolean orderCheck(String orderId);
	
}
