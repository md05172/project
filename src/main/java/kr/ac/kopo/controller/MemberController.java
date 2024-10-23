package kr.ac.kopo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.ac.kopo.service.KakaoService;
import kr.ac.kopo.service.NaverService;

@Controller
@RequestMapping("/member")
public class MemberController {
	
	final String path = "member/";
	
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

}
