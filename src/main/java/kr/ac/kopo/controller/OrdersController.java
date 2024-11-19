package kr.ac.kopo.controller;

import java.util.ArrayList;
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

import kr.ac.kopo.model.Address;
import kr.ac.kopo.model.OrdersDetail;
import kr.ac.kopo.model.Book;
import kr.ac.kopo.model.Buy;
import kr.ac.kopo.service.BookService;

@Controller
@RequestMapping("/order")
public class OrdersController {
	
	@Autowired
	BookService service;
	
	@GetMapping
	String order(Model model, HttpSession session) {
		
		List<Buy> list = (List<Buy>) session.getAttribute("buyList");
		List<Book> bookList = new ArrayList<Book>();
		list.forEach(e -> {
			// bookList에 주문하기에 담긴 bookId를 줘서 책정보를 가져온다 사진을 가져오기 위함
			Book item = service.item(e.getBookid());
			// 수량도 담아준다 원래 Count는 책에 달린 댓글 개수이지만 그냥 쓴다.
			item.setCount(e.getAmount());
			// 주문상품책 정보를 list에 담아준다
			bookList.add(item);
		});		
				
		model.addAttribute("list", bookList);
		
		return "order";
	}
	
	@PostMapping
	void order(@RequestBody Map<Long, Object> items, HttpSession session) {
		List<Buy> list = new ArrayList<Buy>(); 
		
		for (Map.Entry<Long, Object> entry : items.entrySet()) {
			
			Map<String, Object> value = (Map<String, Object>)entry.getValue();
			
	        // value 내부의 각 항목을 처리
	        Buy buy = new Buy();  // 매 항목에 대해 새로운 Buy 객체 생성
	        
	        buy.setBookid(entry.getKey());
	        // 각 속성에 대한 값을 처리
	        buy.setAmount((Integer) value.get("amount")); 
	        buy.setBookname((String) value.get("bookname"));
	        buy.setPrice((Integer) value.get("price"));     
	        buy.setSum((Integer) value.get("sum"));        
	        buy.setWriter((String) value.get("writer"));  
	        
			list.add(buy);
		}
		session.setAttribute("buyList", list);
	}
	
}
