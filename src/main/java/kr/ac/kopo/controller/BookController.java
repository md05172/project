package kr.ac.kopo.controller;

import java.io.File;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import kr.ac.kopo.model.Book;
import kr.ac.kopo.page.Pager;
import kr.ac.kopo.service.BookService;

@Controller
@RequestMapping("/book")
public class BookController {
	
	final String path = "book/";
//	final String uploadPath = "d:/upload/book/";
	final String uploadPath = "c:/upload/book/";
	
	@Autowired
	BookService service;
	
	@GetMapping("/list")
	String list(Model model, Pager pager) {
		model.addAttribute("list", service.list(pager));
		
		return path + "list";
	}
	
	@GetMapping("/detail/{id}")
	String detail(@PathVariable Long id, Model model) {
		Book item = service.item(id);
		
		model.addAttribute("item", item);
	
		return path + "detail";
	}
	
	@GetMapping("/add")
	String add() {
		return path + "add";
	}
	
	@PostMapping("/add")
	String add(Book item, List<MultipartFile> uploadFile) {
		System.out.println(item);
		for (MultipartFile file : uploadFile) {
			if(file != null && !file.isEmpty()) {
				// 파일 원본 이름을 가져온다.
				String filename = file.getOriginalFilename();
				// 랜덤한 문자열을 만들어준다.
				String uuid = UUID.randomUUID().toString();
				String str = "";
				System.out.println(filename);
				System.out.println(uuid);
				
				try {
					// 지정해둔 경로에 원본이름이랑 랜덤한 문자열을 연결해서 저장한다.
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
	
	@GetMapping("/dummy")
	String dummy() {
		service.dummy();
		
		return "redirect:..";
	}
	
	@GetMapping("/init")
	String init() {
		service.init();
		
		return "redirect:..";
	}
	
}
