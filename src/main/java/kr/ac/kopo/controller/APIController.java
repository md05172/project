package kr.ac.kopo.controller;

import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

import kr.ac.kopo.service.KakaoService;
import kr.ac.kopo.service.NaverService;

@Controller
public class APIController {
	
	@Autowired
	private NaverService naverService;
	
	@Autowired
	private KakaoService kakaoService;
	
	@GetMapping("/naver/callback")
	String naverLogin(HttpSession session, String code, String state) throws JsonMappingException, JsonProcessingException, UnsupportedEncodingException {
		System.out.println("naver 들어옴");
		System.out.println("code = " + code);
		System.out.println("state = " + state);
		// 네이버 로그인시 받은code와 state로 토큰을 가져온다.
		String naverToken = naverService.getAccessToken(code, state);
		// 토큰으로 사용자 정보 가져오기
		naverService.getUserInfo(naverToken);
		
		// 가져온 사용자 정보에서 ID, PASSWORD를 조회해서 있는지 확인
//		Map<String, String> map = new HashMap<>();
//		map.put("loginid", customer.getLoginid());
//		map.put("password", customer.getPassword());
//		Customer findCustomer = service.checkIdPassword(map);
//		System.out.println("빠인드커스터머 : " + findCustomer);
		// 조회했을때 정보가 없다면 DB에 정보를 넣는다
//		if(findCustomer == null) {
//			service.joinCustomer(customer);
//		}
		
		// 세션에 정보 담기
//		session.setAttribute("customer", customer);
//		session.setAttribute("naccessToken", naverToken);
		return "redirect:/";
	}
	
//	@GetMapping("/naver/logout") 
//	String naverLogout(HttpServletRequest request) throws ParseException {
//		System.out.println("naver로그아웃");
//		HttpSession session = request.getSession();
//		// 세션에서 네이버 로그인시 저장했던 토큰을 가져온다.
//		String naccessToken = (String) session.getAttribute("naccessToken");
//		boolean result = NaverService.naverLogout(naccessToken);
//		if(result) {
//			session.removeAttribute("naccessToken");
//		}
//		return "redirect:/main";
//	}
	
	@GetMapping("/kakao/callback")
	String kakaoLogin(HttpSession session, String code) throws JsonMappingException, JsonProcessingException {
		System.out.println("kakao 들어옴");
		System.out.println("code = " + code);
		// 받은 코드로 토큰값 가져오기
		String accessToken = kakaoService.getAccessToken(code);
		// 토큰으로 사용자 정보 가져오기
		kakaoService.getUserInfo(accessToken);
		
		return "redirect:/";
	}
	
}
