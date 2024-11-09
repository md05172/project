package kr.ac.kopo.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttribute;

import kr.ac.kopo.model.Customer;
import kr.ac.kopo.model.Review;
import kr.ac.kopo.service.ReviewService;

@RestController
@RequestMapping("/review")
public class ReviewController {

	@Autowired
	ReviewService service;
	
	@PostMapping
	public Review add(@RequestBody Review review, @SessionAttribute Customer customer, HttpSession session) {
		service.add(review);
		
		customer.getReview().add(service.item(review.getId()));
		
		return service.item(review.getId());
	}
	
	@GetMapping("/{bookId}/{select}")
	public List<Review> list(@PathVariable Long bookId, @PathVariable Integer select) {
		Review review = new Review();
		review.setBookId(bookId);
		// 최신순인지 별점순인지
		review.setSelect(select);
		List<Review> list = service.list(review);
		list.get(0).setAvg(service.avg(bookId));
		list.get(0).setCount(service.count(bookId));
		return list;
	}
}
