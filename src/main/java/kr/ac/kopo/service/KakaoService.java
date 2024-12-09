package kr.ac.kopo.service;

import java.util.Map;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import kr.ac.kopo.model.Customer;

@Service
public class KakaoService {

	public static final String KAKAOAPIKEY = "92bef2ab9e055a604cd9efcdafc5551e";
	public static final String REDIRECTURI = "http://172.16.145.10:9090/kakao/callback";
	public static final String LOGOUT = "http://172.16.145.10:9090/kakao/logout";
	public static final String KAKAOURL = "https://kauth.kakao.com/oauth/authorize?response_type=code&" + "client_id="
			+ KAKAOAPIKEY + "&" + "redirect_uri=" + REDIRECTURI + "&" + "response_type=code";
	public static final String KAKAOLOGOUT = "https://kauth.kakao.com/oauth/logout?"
			+ "client_id="+ KAKAOAPIKEY +"&"
			+ "logout_redirect_uri=" + LOGOUT;

	// 토큰가져오기
	public String getAccessToken(String code) throws JsonMappingException, JsonProcessingException  {
		String accessToken = "";

		// header 생성
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.add(HttpHeaders.CONTENT_TYPE, "application/x-www-form-urlencoded;charset=utf-8");

		// body 생성
		MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
		params.add("grant_type", "authorization_code"); // 고정값
		params.add("client_id", KAKAOAPIKEY);
		params.add("redirect_uri", REDIRECTURI); // 등록한 redirect uri
		params.add("code", code);

		// header + body
		HttpEntity<MultiValueMap<String, String>> httpEntity = new HttpEntity<>(params, httpHeaders);

		// http 요청하기
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<String> response = restTemplate.exchange(
				"https://kauth.kakao.com/oauth/token", 
				HttpMethod.POST,
				httpEntity, 
				String.class
				);


		// json으로 바꾸기
		ObjectMapper mapper = new ObjectMapper();
		Map<String, Object> map = mapper.readValue(response.getBody(), new TypeReference<Map<String, Object>>() {});
		System.out.println("토큰 " + map);
		accessToken = (String) map.get("access_token");
		return accessToken;
	}

	// 토큰으로 사용자 정보 가져오기
	public Customer getUserInfo(String accessToken) throws JsonMappingException, JsonProcessingException {
		// HttpHeader 생성
		HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization", "Bearer " + accessToken);
		headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

		// HttpHeader 담기
		RestTemplate rt = new RestTemplate();
		HttpEntity<MultiValueMap<String, String>> httpEntity = new HttpEntity<>(headers);
		ResponseEntity<String> response = rt.exchange(
				"https://kapi.kakao.com/v2/user/me", 
				HttpMethod.POST, 
				httpEntity,
				String.class
				);


		ObjectMapper mapper = new ObjectMapper();
		Map<String, Object> map = mapper.readValue(response.getBody(), new TypeReference<Map<String, Object>>(){});
		Map<String, Object> account = mapper.convertValue(map.get("kakao_account"), new TypeReference<Map<String, Object>>() {});
		Map<String, Object> profile = mapper.convertValue(account.get("profile"), new TypeReference<Map<String, Object>>() {});
		System.out.println("사용자 정보 " + map);
		Customer cust = new Customer();
		cust.setName((String) profile.get("nickname"));
		cust.setEmail((String) account.get("email"));
		cust.setPassword("kakaoApiLogin" + map.get("id"));
		cust.setUserId((Long) map.get("id"));
		cust.setRole(1);
		
		return cust;
	}
	
	// 카카오 로그아웃
	public void kakaoLogout(String accessToken, Long userId) {
		System.out.println("토큰값 + userId값 " + accessToken + "    " + userId);
		// HttpHeader 생성
		HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization", "Bearer " + accessToken);
		headers.add(HttpHeaders.CONTENT_TYPE, "application/x-www-form-urlencoded;charset=utf-8");
		
//		 body 생성
		MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
		params.add("target_id_type", "user_id");
		params.add("target_id", String.valueOf(userId));
		
		
		// HttpHeader + body 담기
		HttpEntity<MultiValueMap<String, String>> httpEntity = new HttpEntity<>(params, headers);
		
		// Http 요청
		RestTemplate rt = new RestTemplate();
		ResponseEntity<String> exchange = rt.exchange(
				"https://kapi.kakao.com/v1/user/unlink",
				HttpMethod.POST,
				httpEntity,
				String.class
				);
		
		System.out.println("화긴여" + exchange);
		System.out.println("화긴여" + exchange.getStatusCodeValue());
		System.out.println("화긴여" + exchange.toString());
		System.out.println("화긴여" + exchange.getHeaders());
		System.out.println("화긴여" + exchange.getStatusCode());
		System.out.println("화긴여" + exchange.getBody());

	}

}
