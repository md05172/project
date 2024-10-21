package kr.ac.kopo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import kr.ac.kopo.service.BookService;

@Controller
public class RootController {
	
	@Autowired
	BookService service;
	
	@GetMapping("/")
	String index(Model model) {
		model.addAttribute("list", service.list());
		return "index";
	}
	
	
}
