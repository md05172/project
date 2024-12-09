package kr.ac.kopo.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import kr.ac.kopo.model.Customer;

public class AdminInterceptor extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		// 세션을 가져온다.
		HttpSession session = request.getSession();
		
		// 회원을 가져온다.
		Customer customer = (Customer) session.getAttribute("customer");
		
		// role이 99면 관리자이다.
		if(customer != null && customer.getRole() == 99) return true;
		
		// 로그인이 안됐으면 보내버린다.
		if(customer == null) {
			response.sendRedirect("/sendLogin");
		} else {
			response.sendRedirect("/");
		}
		return false;
	}
	
}
