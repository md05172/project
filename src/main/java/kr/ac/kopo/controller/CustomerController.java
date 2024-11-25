package kr.ac.kopo.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;

import kr.ac.kopo.model.Customer;
import kr.ac.kopo.model.Mypage;
import kr.ac.kopo.service.CustomerService;
import kr.ac.kopo.service.KakaoService;
import kr.ac.kopo.service.NaverService;
import kr.ac.kopo.service.OrdersService;
import kr.ac.kopo.service.ReviewService;
import kr.ac.kopo.service.WishService;

@Controller
@RequestMapping("/customer")
public class CustomerController {
	
	final String path = "customer/";
	
	@Autowired
	CustomerService service;
	
	@Autowired
	WishService wishService;
	
	@Autowired
	OrdersService ordersService;
	
	@Autowired
	ReviewService reviewService;
	
	@GetMapping("/login") 
	String login(HttpSession session, Model model) {
		model.addAttribute("naver", NaverService.NAVERURL);
		model.addAttribute("kakao", KakaoService.KAKAOURL);
	
		return path + "login";
	}
	
	@PostMapping("/login")
	String login(HttpSession session, Customer item) {
		if(service.login(item)) {
			System.out.println("일반 login " + item);
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
	
	@PostMapping("/phone/{phone}")
	@ResponseBody
	String update(@PathVariable String phone, HttpServletRequest request) {
		System.out.println("확인하자 " + phone);
		HttpSession session = request.getSession();
		Customer customer = (Customer)session.getAttribute("customer");
		customer.setPhone(phone);
		System.out.println("왜 " + customer.getPhone());
		int result = service.phone(customer);
		
		if(result > 0)
			return "ok";
		else
			return "no";
	}
	
	@GetMapping("/mypage")
	String mypage(@SessionAttribute Customer customer, Model model) {

		model.addAttribute("orderCount", ordersService.ordersCount(customer.getId()));
		model.addAttribute("orderSum", ordersService.ordersSum(customer.getId()));
		model.addAttribute("orderBookCount", ordersService.ordersBookCount(customer.getId()));
		model.addAttribute("wishCount", wishService.wishCount(customer.getId()));
		model.addAttribute("reviewCount", reviewService.reviewCount(customer.getId()));
		
		return path + "mypage";
	}
	
	@GetMapping("/mypage/order/list")
	String orderList(@SessionAttribute Customer customer, Model model) {
		List<Mypage> list = service.list(customer.getId());
		
		System.out.println("마이페이지 " + list);
		
		model.addAttribute("mypage", list);
		
		return path + "orderList";
	}
	
	@GetMapping("/mypage/wish/list")
	String wishList(@SessionAttribute Customer customer, Model model) {
		List<Mypage> list = service.list(customer.getId());
		
		System.out.println("마이페이지 " + list);
		
		model.addAttribute("mypage", list);
		
		return path + "wishList";
	}
	
	@GetMapping("/mypage/update")
	String customerUpdate() {
		return path + "update";
	}
	
	@PostMapping("/check/pw/{password}")
	@ResponseBody
	String checkPassword(@SessionAttribute Customer customer, @PathVariable String password) {
		System.out.println("aaa " + password);
		Customer check = service.check(customer.getEmail());
		
		if(check.getPassword().equals(password)) return "ok";
		else return "no";
	}
	
	@PostMapping("/update/pw")
	@ResponseBody
	String updatePassword(@SessionAttribute Customer customer, @RequestBody Map<String, String> password) {
		if(password.get("pw").equals(password.get("repw"))) {
			customer.setPassword(password.get("pw"));
			service.update(customer);
			return "ok";
		} else {
			return "no";
		}
	}
	
}
