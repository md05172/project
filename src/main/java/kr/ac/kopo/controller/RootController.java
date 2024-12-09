package kr.ac.kopo.controller;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import kr.ac.kopo.model.Address;
import kr.ac.kopo.model.Book;
import kr.ac.kopo.model.Customer;
import kr.ac.kopo.model.Orders;
import kr.ac.kopo.model.OrdersDetail;
import kr.ac.kopo.service.BookService;
import kr.ac.kopo.service.CustomerService;
import kr.ac.kopo.service.KakaoService;
import kr.ac.kopo.service.NaverService;
import kr.ac.kopo.service.OrdersService;

@Controller
public class RootController {

	@Autowired
	BookService service;

	@Autowired
	CustomerService custService;
	
	@Autowired
	OrdersService ordersSerivce;

	@Autowired
	private NaverService naverService;
	
	@Autowired
	private KakaoService kakaoService;

	@GetMapping("/")
	String index(Model model) {
		model.addAttribute("kakao_logout", KakaoService.KAKAOLOGOUT);
		model.addAttribute("best", service.best());
		model.addAttribute("novel", service.novel("한강"));

		List<Book> koBookList = service.koBookList();
		Collections.shuffle(koBookList);
		koBookList = koBookList.size() > 3 ? koBookList.subList(0, 3) : koBookList;
		model.addAttribute("koList", koBookList);

		List<Book> nkoBookList = service.nkoBookList();
		Collections.shuffle(nkoBookList);
		nkoBookList = nkoBookList.size() > 3 ? nkoBookList.subList(0, 3) : nkoBookList;
		model.addAttribute("nkoList", nkoBookList);
		return "index";
	}

	// 카카오 로그인
	@GetMapping("/kakao/callback")
	String kakaoLogin(HttpSession session, String code) throws JsonMappingException, JsonProcessingException {
		System.out.println("kakao 들어옴");
		System.out.println("code = " + code);
		// 받은 코드로 토큰값 가져오기
		String accessToken = kakaoService.getAccessToken(code);
		// 토큰으로 사용자 정보 가져오기

		Customer item = kakaoService.getUserInfo(accessToken);
		Long userId = item.getUserId();

		System.out.println("api 확인 " + item);
		
		Customer check = custService.check(item.getEmail());
		
		if(check != null) {
			// 로그인 시킨다
			if(custService.login(item)) {
				item.setApi("kakao");
				item.setUserId(userId);
				session.setAttribute("kaccessToken", accessToken);
				session.setAttribute("customer", item);
				return "redirect:/";
			} else {
				return "redirect:/customer/login";
			}
		} else {
			custService.join(item);
			if(custService.login(item)) {
				item.setApi("kakao");
				item.setUserId(userId);
				session.setAttribute("kaccessToken", accessToken);
				session.setAttribute("customer", item);
				return "redirect:/";
			} else {
				return "redirect:/customer/login";
			}
		}

	}

	// 카카오 로그아웃
	@GetMapping("/kakao/logout")
	String kakaoLogout(HttpServletRequest request) {
		System.out.println("kakao logout 들어옴");
		// 세션에서 카카오톡 로그인시 저장되었던 토큰을 가져온다
		HttpSession session = request.getSession();
		Customer customer = (Customer) session.getAttribute("customer");
		String accessToken = (String) session.getAttribute("kaccessToken");
		System.out.println("가져왔나? " + accessToken);
		// 가져온 토큰으로 카카오톡 로그아웃을 하고
		kakaoService.kakaoLogout(accessToken, customer.getUserId());
		// 세션에서 사용자와 카카오톡 토큰을 없애준다.
		session.invalidate();
		return "redirect:/";
	}

	// 네이버 로그인
	@GetMapping("/naver/callback")
	String naverLogin(HttpSession session, String code, String state)
			throws JsonMappingException, JsonProcessingException, UnsupportedEncodingException {
		if (code == null)
			return "redirect:/";

		System.out.println("naver 들어옴");
		System.out.println("code = " + code);
		System.out.println("state = " + state);
		// 네이버 로그인시 받은code와 state로 토큰을 가져온다.
		String naverToken = naverService.getAccessToken(code, state);
		// 토큰으로 사용자 정보 가져오기
		Customer item = naverService.getUserInfo(naverToken);
		
		System.out.println("api 확인 " + item);
		
		Customer check = custService.check(item.getEmail());
		// 있다는거
		if(check != null) {
			// 로그인 시킨다
			if(custService.login(item)) {
				item.setApi("naver");
				session.setAttribute("naccessToken", naverToken);
				session.setAttribute("customer", item);
				return "redirect:/";
			} else {
				return "redirect:/customer/login";
			}
		} else {
			custService.join(item);
			if(custService.login(item)) {
				item.setApi("naver");
				session.setAttribute("naccessToken", naverToken);
				session.setAttribute("customer", item);
				return "redirect:/";
			} else {
				return "redirect:/customer/login";
			}
		}

	}

	// 네이버 로그아웃
	@GetMapping("/naver/logout")
	String naverLogout(HttpServletRequest request) throws JsonMappingException, JsonProcessingException {
		System.out.println("naver로그아웃");
		HttpSession session = request.getSession();
		// 세션에서 네이버 로그인시 저장했던 토큰을 가져온다.
		String naccessToken = (String) session.getAttribute("naccessToken");
		boolean result = NaverService.naverLogout(naccessToken);
		if (result) {
			session.invalidate();
		}
		return "redirect:/";
	}
	
	@GetMapping("/toss/success")
	String success(Address address, String items, @SessionAttribute Customer customer, String amount, String orderId, Model model) throws JsonMappingException, JsonProcessingException {
		
	    // 이미 주문 완료 상태인지 확인
	    if (ordersSerivce.orderCheck(orderId)) {
	    	System.out.println("주문한 상품");
	        // 주문이 완료된 상태이면, 메인 페이지로 리다이렉트
	        return "redirect:/";
	    }
		
		
		// 주소를 받는 배열을 만들고
		List<Address> add = new ArrayList<Address>();
		add.add(address);
		
		// 배열을 고객Model에 넣어준다
		customer.setAddress(add);
		
		// 주소가담긴 고객을 넘겨줘서 address테이블 insert한다.
		// 회원아이디를 저장하고 저장한 address객체를 넘겨서 중복확인을 한다. 
		customer.getAddress().get(0).setCustId(customer.getId());
		Address check = ordersSerivce.check(customer.getAddress().get(0));
		System.out.println("check 확인 " + check);
		
		if(check == null) // 주소가 중복이 아니면 주소를 넣는다.
			ordersSerivce.address(customer);
		else // 주소가 중복이면 check가 가지고 있는 address id값을 넣어준다.
			customer.getAddress().get(0).setId(check.getId());
			
		// json으로 받은 bookid 수량을 객체로 받는다.
		ObjectMapper mapper = new ObjectMapper();
		List<OrdersDetail> item = mapper.readValue(items, mapper.getTypeFactory().constructCollectionType(List.class, OrdersDetail.class));
		
		// address id값이 담긴 값 넣어주어 orders테이블에 insert한다.
		Orders orders = new Orders();
		orders.setCustId(customer.getId());
		orders.setAddressId(customer.getAddress().get(0).getId());
		orders.setCode(orderId);
		orders.setDetails(item);
		
		ordersSerivce.add(orders);
		
		// 주문번호
		model.addAttribute("orderId", orderId);
		// 총가격
		model.addAttribute("amount", amount);
		// 주소
		model.addAttribute("address", address);
		// 구매상품
		List<Book> bookList = new ArrayList<Book>();
		item.forEach(e -> {
			// bookList에 주문하기에 담긴 bookId를 줘서 책정보를 가져온다 사진을 가져오기 위함
			Book book = service.item(e.getBookId());
			// 수량도 담아준다 원래 Count는 책에 달린 댓글 개수이지만 그냥 쓴다.
			book.setCount(e.getAmount());
			// 주문상품책 정보를 list에 담아준다
			bookList.add(book);
		});	
		
		model.addAttribute("bookList", bookList);
		
		return "success";
	}
	
	@GetMapping("/sendLogin")
	String sendLogin() {
		return "sendLogin";
	}
	
}
