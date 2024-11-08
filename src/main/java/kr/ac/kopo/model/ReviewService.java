package kr.ac.kopo.model;

import java.util.List;

public interface ReviewService {

	public void add(Review review);
	
	public List<Review> list();

	public Review item(Long id);

}
