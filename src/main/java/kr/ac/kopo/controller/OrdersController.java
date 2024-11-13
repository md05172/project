package kr.ac.kopo.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.ac.kopo.model.Buy;

@Controller
@RequestMapping("/order")
public class OrdersController {
	
	@GetMapping
	String order(Model model, HttpSession session) {
		System.out.println("세션값" + session.getAttribute("buyList"));
		model.addAttribute("list", session.getAttribute("buyList"));
		session.removeAttribute("buyList");
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
