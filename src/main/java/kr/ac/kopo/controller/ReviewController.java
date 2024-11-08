package kr.ac.kopo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.ac.kopo.model.Review;
import kr.ac.kopo.model.ReviewService;

@RestController
@RequestMapping("/review")
public class ReviewController {

	@Autowired
	ReviewService service;
	
	@PostMapping
	public Review add(@RequestBody Review review) {
		service.add(review);
		
		return service.item(review.getId());
	}
	
	@GetMapping
	public List<Review> list() {
	
		return service.list();
	}
}
