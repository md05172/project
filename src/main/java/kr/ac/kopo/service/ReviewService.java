package kr.ac.kopo.service;

import java.util.List;

import kr.ac.kopo.model.Customer;
import kr.ac.kopo.model.Review;

public interface ReviewService {

	public void add(Review review);

	public List<Review> list(Review review);

	public Review item(Long id);

	public Customer customerReview(Customer customer);

	public Double avg(Long bookId);

	public Integer count(Long bookId);

}
