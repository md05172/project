package kr.ac.kopo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import kr.ac.kopo.service.BookService;
import kr.ac.kopo.service.KakaoService;

@Controller
public class RootController {
	
	@Autowired
	BookService service;
	
	@GetMapping("/")
	String index(Model model) {
		model.addAttribute("kakao_logout", KakaoService.KAKAOLOGOUT);
		return "index";
	}
}
