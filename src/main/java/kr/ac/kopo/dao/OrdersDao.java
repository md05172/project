package kr.ac.kopo.dao;

import kr.ac.kopo.model.Address;
import kr.ac.kopo.model.Customer;
import kr.ac.kopo.model.Orders;
import kr.ac.kopo.model.OrdersDetail;

public interface OrdersDao {

	public Address check(Address address);
	
	public void address(Customer item);

	public void add(Orders orders);

	public void addDetail(OrdersDetail detail);

	public boolean orderCheck(String orderId);

	public Integer ordersCount(Long custId);

	public Integer ordersSum(Long custId);
	
	public Integer ordersBookCount(Long custId);

}
