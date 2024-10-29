package kr.ac.kopo.controller;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

import kr.ac.kopo.model.Api;
import kr.ac.kopo.service.ApiService;
import kr.ac.kopo.service.KakaoService;
import kr.ac.kopo.service.NaverService;

@Controller
public class ApiController {
	
	@Autowired
	ApiService service;
	
	@Autowired
	private NaverService naverService;
	
	@Autowired
	private KakaoService kakaoService;
	
	// 네이버 로그인
	@GetMapping("/naver/callback")
	String naverLogin(HttpSession session, String code, String state) throws JsonMappingException, JsonProcessingException, UnsupportedEncodingException {
		if(code == null) return "redirect:../";
		
		System.out.println("naver 들어옴");
		System.out.println("code = " + code);
		System.out.println("state = " + state);
		// 네이버 로그인시 받은code와 state로 토큰을 가져온다.
		String naverToken = naverService.getAccessToken(code, state);
		// 토큰으로 사용자 정보 가져오기
		Api api = naverService.getUserInfo(naverToken);
		System.out.println("api 확인 " + api);
		
		Api item = service.item(api.getEmail());
		if(item != null) {
			session.setAttribute("api", item);
			session.setAttribute("naceessToken", naverToken);
		} else {
			service.join(api);
			session.setAttribute("api", item);
			session.setAttribute("naceessToken", naverToken);
		}
		
		return "redirect:../";
	}
	
	// 네이버 로그아웃
	@GetMapping("/naver/logout") 
	String naverLogout(HttpServletRequest request) throws JsonMappingException, JsonProcessingException  {
		System.out.println("naver로그아웃");
		HttpSession session = request.getSession();
		// 세션에서 네이버 로그인시 저장했던 토큰을 가져온다.
		String naccessToken = (String) session.getAttribute("naceessToken");
		boolean result = NaverService.naverLogout(naccessToken);
		if(result) {
			session.removeAttribute("api");
			session.removeAttribute("naceessToken");
		}
		return "redirect:../";
	}
	
	// 카카오 로그인
	@GetMapping("/kakao/callback")
	String kakaoLogin(HttpSession session, String code) throws JsonMappingException, JsonProcessingException {
		System.out.println("kakao 들어옴");
		System.out.println("code = " + code);
		// 받은 코드로 토큰값 가져오기
		String accessToken = kakaoService.getAccessToken(code);
		// 토큰으로 사용자 정보 가져오기
		Api api = kakaoService.getUserInfo(accessToken);
		System.out.println("api 확인 " + api);
		
		Api item = service.item(api.getEmail());
		if(item != null) {
			session.setAttribute("api", item);
			session.setAttribute("kaccessToken", accessToken);
		} else {
			service.join(api);
			session.setAttribute("api", item);
			session.setAttribute("kaccessToken", accessToken);
		}
		
		return "redirect:../";
	}
	
	// 카카오 로그아웃
	@GetMapping("/kakao/logout")
	String kakaoLogout(HttpSession session) {
		System.out.println("kakao logout 들어옴");
		// 세션에서 카카오톡 로그인시 저장되었던 토큰을 가져온다
		String accessToken = (String)session.getAttribute("kaccessToken");
		System.out.println("가져왔나? " + accessToken);
		// 가져온 토큰으로 카카오톡 로그아웃을 하고
		kakaoService.kakaoLogout(accessToken);
		// 세션에서 사용자와 카카오톡 토큰을 없애준다.
		session.removeAttribute("api");
		session.removeAttribute("kaccessToken");
		return "redirect:../";
	}
	
}
