package kr.ac.kopo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.ac.kopo.dao.ReviewDao;
import kr.ac.kopo.model.Customer;
import kr.ac.kopo.model.Review;

@Service
public class ReviewServiceImpl implements ReviewService{

	@Autowired
	ReviewDao dao;
	
	@Override
	public void add(Review review) {
		dao.add(review);
	}

	@Override
	public List<Review> list(Review review) {
		return dao.list(review);
	}

	@Override
	public Review item(Long id) {
		return dao.item(id);
	}

	@Override
	public Customer customerReview(Customer customer) {
		return dao.customerReview(customer);
	}

	@Override
	public Double avg(Long bookId) {
		return dao.avg(bookId);
	}

	@Override
	public Integer count(Long bookId) {
		return dao.count(bookId);
	}

	@Override
	public Integer reviewCount(Long custId) {
		return dao.reviewCount(custId);
	}

}
