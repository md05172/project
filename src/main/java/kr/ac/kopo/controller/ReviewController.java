package kr.ac.kopo.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.ac.kopo.model.Review;

@RestController
@RequestMapping("/review")
public class ReviewController {

	@PostMapping
	public Review add(@RequestBody Review review) {
		System.out.println("review " + review);
		return review;
	}
}
