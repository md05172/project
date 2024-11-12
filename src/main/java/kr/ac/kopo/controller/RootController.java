package kr.ac.kopo.controller;

import java.io.UnsupportedEncodingException;
import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

import kr.ac.kopo.model.Book;
import kr.ac.kopo.model.Customer;
import kr.ac.kopo.service.BookService;
import kr.ac.kopo.service.CustomerService;
import kr.ac.kopo.service.KakaoService;
import kr.ac.kopo.service.NaverService;

@Controller
public class RootController {

	@Autowired
	BookService service;

	@Autowired
	CustomerService custService;

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
		System.out.println("api 확인 " + item);
		
		Customer check = custService.check(item.getEmail());
		
		if(check != null) {
			// 로그인 시킨다
			if(custService.login(item)) {
				session.setAttribute("customer", item);
				return "redirect:/";
			} else {
				return "redirect:/customer/login";
			}
		} else {
			custService.join(item);
			if(custService.login(item)) {
				session.setAttribute("customer", item);
				return "redirect:/";
			} else {
				return "redirect:/customer/login";
			}
		}

	}

	// 카카오 로그아웃
	@GetMapping("/kakao/logout")
	String kakaoLogout(HttpSession session) {
		System.out.println("kakao logout 들어옴");
		// 세션에서 카카오톡 로그인시 저장되었던 토큰을 가져온다
		String accessToken = (String) session.getAttribute("kaccessToken");
		System.out.println("가져왔나? " + accessToken);
		// 가져온 토큰으로 카카오톡 로그아웃을 하고
		kakaoService.kakaoLogout(accessToken);
		// 세션에서 사용자와 카카오톡 토큰을 없애준다.
		session.removeAttribute("customer");
		session.removeAttribute("kaccessToken");
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
				session.setAttribute("customer", item);
				return "redirect:/";
			} else {
				return "redirect:/customer/login";
			}
		} else {
			custService.join(item);
			if(custService.login(item)) {
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
		String naccessToken = (String) session.getAttribute("naceessToken");
		boolean result = NaverService.naverLogout(naccessToken);
		if (result) {
			session.removeAttribute("api");
			session.removeAttribute("naceessToken");
		}
		return "redirect:/";
	}

}
