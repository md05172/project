package kr.ac.kopo.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
	
	@ResponseBody
	@PostMapping("/add")
	Cart add(@RequestBody Map<Long, Integer> items, @SessionAttribute(required = false) Cart cart, HttpSession session) {
		System.out.println("장밥구니 들어옴 + " + items);
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
	
	@DeleteMapping("/{id}")
	@ResponseBody
	void delete(@PathVariable Long id, @SessionAttribute Cart cart) {
		cart.delete(id);
	}

}
