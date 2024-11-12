package kr.ac.kopo.dao;

import java.util.List;

import kr.ac.kopo.model.Customer;
import kr.ac.kopo.model.Review;

public interface ReviewDao {

	public void add(Review review);

	public List<Review> list(Review review);

	public Review item(Long id);

	public Customer customerReview(Customer customer);

	public Double avg(Long bookId);

	public Integer count(Long bookId);

}
