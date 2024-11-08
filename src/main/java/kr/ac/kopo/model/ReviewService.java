package kr.ac.kopo.model;

import java.util.List;

public interface ReviewService {

	public void add(Review review);
	
	public List<Review> list(Review review);

	public Review item(Long id);

}
