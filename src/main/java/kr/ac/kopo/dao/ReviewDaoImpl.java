package kr.ac.kopo.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.ac.kopo.model.Customer;
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
	public List<Review> list(Review review) {
		return sql.selectList("review.list", review);
	}

	@Override
	public Review item(Long id) {
		return sql.selectOne("review.item", id);
	}

	@Override
	public Customer customerReview(Customer customer) {
		return sql.selectOne("review.customer_review", customer);
	}

	@Override
	public Double avg(Long bookId) {
		return sql.selectOne("review.avg", bookId);
	}

	@Override
	public Integer count(Long bookId) {
		return sql.selectOne("review.count", bookId);
	}

	@Override
	public Integer reviewCount(Long custId) {
		return sql.selectOne("review.review_count", custId);
	}

	@Override
	public int delete(Long reviewId) {
		return sql.delete("review.delete", reviewId);
	}

}
