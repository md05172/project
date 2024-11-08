package kr.ac.kopo.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.ac.kopo.model.Review;

@Repository
public class ReviewDaoImpl implements ReviewDao{
	
	@Autowired
	SqlSession sql;

	@Override
	public void add(Review review) {
		sql.insert("review.add", review);
	}

	@Override
	public List<Review> list() {
		return sql.selectList("review.list");
	}

	@Override
	public Review item(Long id) {
		return sql.selectOne("review.item", id);
	}
	

}
