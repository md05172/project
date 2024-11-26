package kr.ac.kopo.service;

import java.io.UnsupportedEncodingException;
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
public class NaverService {

	public static final String NAVERAPIKEY = "8cgsISIdMiFJbJa7Crpa";
	public static final String REDIRECTURI = "http://172.16.145.10:9090/naver/callback";
	public static final String NAVERSECRET = "DSpCfIN9zV";
	public static final String NAVERURL = "https://nid.naver.com/oauth2.0/authorize?response_type=code&" + "client_id="
			+ NAVERAPIKEY + "&" + "state=STATE_STRING&" + "redirect_uri=" + REDIRECTURI;

	// 네이버 토큰 가져오기
	public String getAccessToken(String code, String state) throws UnsupportedEncodingException, JsonMappingException, JsonProcessingException {
		String accessToken = "";

		// header 생성
		HttpHeaders headers = new HttpHeaders();
		
		// body 생성
		MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
		params.add("grant_type", "authorization_code");
		params.add("client_id", NAVERAPIKEY);
		params.add("client_secret", NAVERSECRET);
		params.add("code", code);
		params.add("state", state);

		// header + body 
		HttpEntity<MultiValueMap<String, String>> httpEntity = new HttpEntity<>(params, headers);

		// http요청
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<String> response = restTemplate.exchange(
				"https://nid.naver.com/oauth2.0/token", 
				HttpMethod.POST,
				httpEntity, 
				String.class
				);

		System.out.println("naver response = " + response.getBody());

		// josn으로 변환
		ObjectMapper mapper = new ObjectMapper();
		Map<String, Object> jsonMap = mapper.readValue(response.getBody(), new TypeReference<Map<String, Object>>() {});
		accessToken = (String) jsonMap.get("access_token");

		return accessToken;
	}
	
	// 네이버 토큰으로  유저 정보 가져오기
	public Customer getUserInfo(String accessToken) throws JsonMappingException, JsonProcessingException {
		// HttpHeader 생성
		HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization", "Bearer " + accessToken);

		// HttpHeader 담기
		RestTemplate restTemplate = new RestTemplate();
		HttpEntity<MultiValueMap<String, String>> httpEntity = new HttpEntity<>(headers);
		ResponseEntity<String> response = restTemplate.exchange(
				"https://openapi.naver.com/v1/nid/me", 
				HttpMethod.POST,
				httpEntity, 
				String.class);

		System.out.println("naver getUserInfo = " + response.getBody());
		
		// json으로 변환하기
		ObjectMapper mapper = new ObjectMapper();
		Map<String, Object> jsonMap = mapper.readValue(response.getBody(), new TypeReference<Map<String, Object>>() {});
		Map<String, Object> response2 = mapper.convertValue(jsonMap.get("response"), new TypeReference<Map<String, Object>>() {});
		
		Customer cust = new Customer();
		cust.setName((String) response2.get("name"));
		cust.setEmail((String) response2.get("email"));
		cust.setPassword("naverApiLogin" + response2.get("id"));
		cust.setPhone((String)response2.get("mobile"));
		cust.setRole(1);
		
		return cust;
	}

	// 네이버 로그아웃
	public static boolean naverLogout(String accessToken) throws JsonMappingException, JsonProcessingException {
		HttpHeaders headers = new HttpHeaders();

		MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
		params.add("grant_type", "delete");
		params.add("client_id", NAVERAPIKEY);
		params.add("client_secret", NAVERSECRET);
		params.add("access_token", accessToken);
		params.add("service_provider", "NAVER");

		HttpEntity<MultiValueMap<String, String>> httpEntity = new HttpEntity<>(params, headers);

		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<String> response = restTemplate.exchange(
				"https://nid.naver.com/oauth2.0/token", 
				HttpMethod.POST,
				httpEntity, 
				String.class
				);
		System.out.println(response.getBody());
		
		ObjectMapper mapper = new ObjectMapper();
		Map<String, Object> jsonMap = mapper.readValue(response.getBody(), new TypeReference<Map<String, Object>>() {});
		String result = (String)jsonMap.get("result");
		
		System.out.println("result = " + result);
		
		return result == null ? false : true;
	}

	// 유니코드 한글 또는 영어로 변환
	public String uniToKor(String uni) {
		StringBuffer result = new StringBuffer();

		for (int i = 0; i < uni.length(); i++) {
			if (uni.charAt(i) == '\\' && uni.charAt(i + 1) == 'u') {
				Character c = (char) Integer.parseInt(uni.substring(i + 2, i + 6), 16);
				result.append(c);
				i += 5;
			} else {
				result.append(uni.charAt(i));
			}
		}
		return result.toString();
	}

	
}
