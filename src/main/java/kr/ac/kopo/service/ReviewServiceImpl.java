package kr.ac.kopo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.ac.kopo.dao.ReviewDao;
import kr.ac.kopo.model.Review;
import kr.ac.kopo.model.ReviewService;

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

}
