package kr.ac.kopo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.ac.kopo.model.Wish;
import kr.ac.kopo.service.WishService;

@RestController
@RequestMapping("/cart")
public class CartController {
	
	@Autowired
	WishService service;
	
	@PostMapping
	String wish(@RequestBody Wish wish) {
		System.out.println("왔나?");
		System.out.println(wish);
		int success = service.add(wish);
		if(success > 0) return "ok";
		 else return "no";
	}
}
