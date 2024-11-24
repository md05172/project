package kr.ac.kopo.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.ac.kopo.model.Customer;
import kr.ac.kopo.model.Mypage;

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

	@Override
	public int phone(Customer customer) {
		return sql.update("customer.phone", customer);
	}

	@Override
	public List<Mypage> list(Long id) {
		return sql.selectList("customer.list", id);
	}

}
