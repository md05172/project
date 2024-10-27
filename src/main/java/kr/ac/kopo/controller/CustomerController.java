package kr.ac.kopo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.ac.kopo.model.Customer;
import kr.ac.kopo.service.KakaoService;
import kr.ac.kopo.service.CustomerService;
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
	
	@GetMapping("/join")
	String join() {
		return path + "join";
	}
	
	@PostMapping("/join")
	String join(Customer item) {
		service.join(item);
		return "redirect:login";
	}
	
	@PostMapping("/check/{email}")
	@ResponseBody
	String check(@PathVariable String email) {
		Customer check = service.check(email);
		if(check == null) return "ok";
		else return "no";
	}
}
