package kr.ac.kopo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttribute;

import kr.ac.kopo.model.Customer;
import kr.ac.kopo.model.Wish;
import kr.ac.kopo.service.WishService;

@RestController
@RequestMapping("/wish")
public class WishController {
	
	@Autowired
	WishService service;
	
	@PostMapping
	String wish(@RequestBody Wish wish) {
		int success = service.add(wish);
		if(success > 0) return "ok";
		 else return "no";
	}
	
	@DeleteMapping("/{id}")
	String delete(@PathVariable Long id, @SessionAttribute Customer customer) {
		System.out.println(customer);
		customer.getWish().removeIf(e -> e.getId() == id);
		int delete = service.delete(id);
		
		if(delete > 0) return "ok";
		
		return "no";
	}
}
