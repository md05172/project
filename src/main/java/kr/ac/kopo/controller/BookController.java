package kr.ac.kopo.controller;

import java.io.File;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import kr.ac.kopo.model.Book;
import kr.ac.kopo.service.BookService;

@Controller
@RequestMapping("/book")
public class BookController {
	
	final String path = "book/";
	final String uploadPath = "d:/upload/book/";
	
	@Autowired
	BookService service;
	
	@GetMapping("/list")
	String list(Model model) {
		model.addAttribute(path, service.list());
		
		return path + "list";
	}
	
	@GetMapping("/add")
	String add() {
		return path + "add";
	}
	
	@PostMapping("/add")
	String add(Book item, List<MultipartFile> uploadFile) {
		System.out.println("확인 " + item);
		System.out.println("확인 " + uploadFile);
		for (MultipartFile file : uploadFile) {
			if(file != null && !file.isEmpty()) {
				String filename = file.getOriginalFilename();
				String uuid = UUID.randomUUID().toString();
				String str = "";
				System.out.println(filename);
				System.out.println(uuid);
				
				try {
					file.transferTo(new File(uploadPath + uuid + "_" + filename));
					
					str = uuid + "_" + filename;
					item.setPath(str);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		
		service.add(item);					
		
		return "redirect:../";
	}
	
}
