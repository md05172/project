package kr.ac.kopo.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.ac.kopo.model.Customer;
import kr.ac.kopo.service.CustomerService;
import kr.ac.kopo.service.KakaoService;
import kr.ac.kopo.service.NaverService;

@Controller
@RequestMapping("/customer")
public class CustomerController {
	
	final String path = "customer/";
	
	@Autowired
	CustomerService service;
	
	@GetMapping("/login") 
	String login(Model model) {
		model.addAttribute("naver", NaverService.NAVERURL);
		model.addAttribute("kakao", KakaoService.KAKAOURL);
		
		return path + "login";
	}
	
	@PostMapping("/login")
	String login(HttpSession session, Customer item) {
		Customer customer = service.check(item.getEmail());
		System.out.println("로그인  확인" + customer);
		
		return "";
	}
	
	@GetMapping("/join")
	String join() {
		return path + "join";
	}
	
	@PostMapping("/join")
	String join(Customer item) {
		service.join(item);
		return "redirect:login";
	}
	
	@GetMapping("/check/{email}")
	@ResponseBody
	String check(@PathVariable String email) {
		System.out.println("이메일 확인" + email);
		Customer check = service.check(email);
		System.out.println("확인  "   + check);
		if(check == null) return "ok";
		else return "no";
	}
}
