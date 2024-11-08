package kr.ac.kopo.dao;

import java.util.List;

import kr.ac.kopo.model.Review;

public interface ReviewDao {

	void add(Review review);

	List<Review> list(Review review);

	Review item(Long id);
	
}
