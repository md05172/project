package kr.ac.kopo.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.ac.kopo.model.Api;

@Repository
public class ApiDaoImpl implements ApiDao{

	@Autowired
	SqlSession sql;
	
	@Override
	public void join(Api api) {
		sql.insert("api.join", api);
	}

	@Override
	public Api item(String email) {
		return sql.selectOne("api.item", email);
	}

}
