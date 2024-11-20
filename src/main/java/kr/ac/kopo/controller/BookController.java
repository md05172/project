package kr.ac.kopo.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.multipart.MultipartFile;

import kr.ac.kopo.model.Book;
import kr.ac.kopo.model.Customer;
import kr.ac.kopo.page.Pager;
import kr.ac.kopo.service.BookService;
import kr.ac.kopo.service.ReviewService;

@Controller
@RequestMapping("/book")
public class BookController {
	
	final String path = "book/";
	final String uploadPath = "d:/upload/book/";
//	final String uploadPath = "c:/upload/book/";
	
	@Autowired
	BookService service;
	
	@Autowired
	ReviewService reviewService;
	
	@GetMapping("/list/{category}")
	String list(@PathVariable String category, Model model, Pager pager, @SessionAttribute(required = false) Customer customer) {
		pager.setCategory(category);
		
		model.addAttribute("list", service.list(pager));
		if(customer != null) {
			model.addAttribute("wish", customer.getWish());
		} else {
			model.addAttribute("wish", new ArrayList<>());
		}
		
		return path + "list";
	}
	
	@GetMapping("/detail/{id}")
	String detail(@PathVariable Long id, Model model) {
		Book item = service.item(id);
		
		Pager pager = new Pager();
		pager.setPerPage(40);
		pager.setCategory(item.getCategory());
		List<Book> list = service.list(pager);
		
		Collections.shuffle(list); // 섞어준다.
		
		// 거기서 5개만 가지고 온다
		list = list.size() > 5 ? list.subList(0, 5) : list;
		
		list.forEach(book -> {
			// 책에 대한 평균을 가져와서 Book객체에 있는 avg에 값을 넣어준다.
			book.setAvg(reviewService.avg(book.getId()));	
			book.setCount(reviewService.count(book.getId()));
		});
		
		model.addAttribute("item", item);
		model.addAttribute("relatedList", list);
	
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
	
	@GetMapping("/search/{bookName}")
	@ResponseBody
	List<Book> searchKeyword (@PathVariable String bookName) {
		System.out.println("들어옴");
		Pager pager = new Pager();
		pager.setPerPage(0);
		pager.setBookName(bookName);
		
		List<Book> list = service.list(pager);
		
		System.out.println(list);
		
		return list;
	}
	
	@GetMapping("/update/{id}")
	String update(@PathVariable Long id, Model model) {
		model.addAttribute("item", service.item(id));
		return path + "update";
	}
	
	@PostMapping("/update/{id}")
	String update(Book item, @PathVariable Long id, List<MultipartFile> uploadFile) {
		// 기존에 있던 사진과 다른 사진인지 알아야함.
		Book book = service.item(id);
		
		// 새로운 사진으로 등록
		for (MultipartFile file : uploadFile) {
			if(file != null && !file.isEmpty()) {
				// 파일 원본 이름을 가져온다.
				String filename = file.getOriginalFilename();
				// 랜덤한 문자열을 만들어준다.
				String uuid = UUID.randomUUID().toString();
				String str = "";
				System.out.println(filename);
				System.out.println(uuid);
				
				// 기존에 있던 사진이랑 다르면 기존에 있는 사진을 지운다
				if(!book.getPath().substring(book.getPath().indexOf("_")+1).equals(filename) || !book.getPath().equals(filename)) {
					File beforFile = new File(uploadPath + book.getPath());
					beforFile.delete();
					
					try {
						// 지정해둔 경로에 원본이름이랑 랜덤한 문자열을 연결해서 저장한다.
						file.transferTo(new File(uploadPath + uuid + "_" + filename));
						
						str = uuid + "_" + filename;
						item.setPath(str);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			} else {
				// 사진을 바꾸지 않으면 그냥 기존에 있는 사진을 쓴다.
				item.setPath(book.getPath());
			}
		}
			
		item.setId(id);
		
		service.update(item);
		
		return "redirect:../list/" + item.getCategory();
	}
	
	@GetMapping("/delete/{id}")
	String delete(@PathVariable Long id) {
		Book item = service.item(id);
		
		service.delete(id);
		File file = new File(uploadPath + item.getPath());
		file.delete();
		
		return "redirect:../list/" + item.getCategory();
	}
	
}
