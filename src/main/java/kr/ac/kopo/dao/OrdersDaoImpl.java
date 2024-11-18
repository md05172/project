package kr.ac.kopo.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.ac.kopo.model.Customer;
import kr.ac.kopo.model.Orders;

@Repository
public class OrdersDaoImpl implements OrdersDao{

	@Autowired
	SqlSession sql;
	
	@Override
	public Orders check(Customer customer) {
		return sql.selectOne("orders.check", customer);
	}

}
