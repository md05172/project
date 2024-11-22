package kr.ac.kopo.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.ac.kopo.model.Address;
import kr.ac.kopo.model.Customer;
import kr.ac.kopo.model.Orders;
import kr.ac.kopo.model.OrdersDetail;

@Repository
public class OrdersDaoImpl implements OrdersDao{

	@Autowired
	SqlSession sql;
	
	@Override
	public Address check(Address address) {
		return sql.selectOne("orders.check", address);
	}
	
	@Override
	public void address(Customer item) {
		sql.insert("orders.address", item);
	}

	@Override
	public void add(Orders orders) {
		sql.insert("orders.add", orders);
	}

	@Override
	public void addDetail(OrdersDetail detail) {
		sql.insert("orders.add_detail", detail);
	}

	@Override
	public boolean orderCheck(String orderId) {
		Orders selectOne = sql.selectOne("orders.order_check", orderId);
		System.out.println("selectOne " + selectOne);
		
		// selectOne이 있으면 주문을 했다는것 없다면 주문안한것
		return selectOne != null ? true : false;
	}

	@Override
	public List<Orders> list(Long customerId) {
		return sql.selectList("orders.list", customerId);
	}

}
