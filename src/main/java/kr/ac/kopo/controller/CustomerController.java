package kr.ac.kopo.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.ac.kopo.model.Customer;
import kr.ac.kopo.service.CustomerService;
import kr.ac.kopo.service.KakaoService;
import kr.ac.kopo.service.NaverService;
import kr.ac.kopo.service.WishService;

@Controller
@RequestMapping("/customer")
public class CustomerController {
	
	final String path = "customer/";
	
	@Autowired
	CustomerService service;
	@Autowired
	WishService wishService;
	
	@GetMapping("/login") 
	String login(HttpSession session, Model model) {
		model.addAttribute("naver", NaverService.NAVERURL);
		model.addAttribute("kakao", KakaoService.KAKAOURL);
	
		return path + "login";
	}
	
	@PostMapping("/login")
	String login(HttpSession session, Customer item) {
		if(service.login(item)) {
			session.setAttribute("customer", item);
			return "redirect:../";
		} else {
			return "redirect:login";
		}
	}
	
	@GetMapping("/logout")
	String logout(HttpSession session) {
		session.invalidate();
		
		return "redirect:../";
	}
	
	@GetMapping("/join")
	String join() {
		return path + "join";
	}
	
	@PostMapping("/join")
	String join(HttpSession session, Customer item) {
		System.out.println(item);
		service.join(item);
		return "redirect:login";
	}
	
	@PostMapping("/check")
	@ResponseBody
	String check(String email) {
		Customer check = service.check(email);
		if(check == null) return "ok";
		else return "no";
	}
}
