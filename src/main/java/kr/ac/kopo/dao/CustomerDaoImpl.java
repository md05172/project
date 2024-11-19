package kr.ac.kopo.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.ac.kopo.model.Customer;

@Repository
public class CustomerDaoImpl implements CustomerDao{
	
	@Autowired
	SqlSession sql;

	@Override
	public void join(Customer item) {
		sql.insert("customer.join", item);
	}

	@Override
	public Customer check(String email) {
		return sql.selectOne("customer.check", email);
	}

}
