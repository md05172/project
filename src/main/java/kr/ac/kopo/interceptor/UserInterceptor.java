package kr.ac.kopo.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import kr.ac.kopo.model.Customer;

public class UserInterceptor extends HandlerInterceptorAdapter{
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		// 세션을 가져온다
		HttpSession session = request.getSession();
		
		// 세션에서 회원이 있는지 확인한다
		Customer customer = (Customer) session.getAttribute("customer");
		
		// 회원이 있으면 로그인이 된것
		if(customer != null) return true;
		
		response.sendRedirect("/sendLogin");
		
		return false;
	}
}
