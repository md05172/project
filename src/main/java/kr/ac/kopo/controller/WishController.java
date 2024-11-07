package kr.ac.kopo.controller;

import javax.servlet.http.HttpSession;

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
	Customer wish(@RequestBody Wish wish, @SessionAttribute Customer customer, HttpSession session) {
		service.add(wish); // 등록하고
		customer = service.wishList(customer); // 다시 목록을 가져와서 customer에 넣어준다
		
		System.out.println("찜등록 " + customer);
		
		session.setAttribute("customer", customer);
		return customer;
	}
	
	@DeleteMapping("/{id}")
	String delete(@PathVariable Long id, @SessionAttribute Customer customer) {
		System.out.println("삭제전 " + customer);
		customer.getWish().removeIf(e -> e.getId() == id);
		
		System.out.println("삭제후 " + customer);
		int delete = service.delete(id);
		
		if(delete > 0) return "ok";
		
		return "no";
	}
}
