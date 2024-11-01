package kr.ac.kopo.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.ac.kopo.model.Customer;
import kr.ac.kopo.model.Wish;

@Repository
public class WishDaoImpl implements WishDao{
	
	@Autowired
	SqlSession sql;
	
	@Override
	public int add(Wish wish) {
		return sql.insert("wish.add", wish);
	}

	@Override
	public Customer wishList(Customer item) {
	return sql.selectOne("wish.list", item);
	}

}
