package kr.ac.kopo.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;

import kr.ac.kopo.model.Book;
import kr.ac.kopo.model.Cart;
import kr.ac.kopo.service.BookService;

@Controller
@RequestMapping("/cart")
public class CartController {
	
	@Autowired
	BookService service;
	
	@GetMapping
	String cart(@SessionAttribute(required = false) Cart cart, Model model) {
		if(cart == null) cart = new Cart();
		
		// cart에 담긴 key값 (bookId값만 넘겨준다)
		List<Book> list = service.list(cart.getCart().keySet());
		
		model.addAttribute("list", list);
		return "cart";
	}
	
	@GetMapping("/list")
	@ResponseBody
	Cart cart(@SessionAttribute(required = false) Cart cart) {
		System.out.println("들어옴");
		if(cart == null) cart = new Cart();
		
		return cart;
	}
	
	@ResponseBody
	@PostMapping("/add")
	Cart add(@RequestBody Map<Long, Integer> items, @SessionAttribute(required = false) Cart cart, HttpSession session) {
		if(cart == null) {
			cart = new Cart();
			session.setAttribute("cart", cart);
		}
			
		for (Map.Entry<Long, Integer> entry : items.entrySet()) {
			Long bookid = entry.getKey();
			Integer amount = entry.getValue();

			cart.setCart(bookid, amount);
		}
		return cart;
	}
	
	@PostMapping("/delete")
	@ResponseBody
	Cart delete(@RequestBody Map<Long, Integer> items, @SessionAttribute Cart cart) {
		for (Map.Entry<Long, Integer> entry : items.entrySet()) {
			Long bookid = entry.getKey();
			cart.delete(bookid);
		}
		return cart;
	}

}
