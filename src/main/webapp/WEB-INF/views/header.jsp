<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<header>
	<div class="head_container">
		<!-- 로고 -->
		<div class="logo">
			<a href="/"><img src="/resources/images/logo.png" alt="로고"></a>
		</div>
		
		<!-- 검색 -->
		<div class="search_container">
			<div class="search_wrap">
				<div class="search_box">
						<input id="keyword">
						<div class="search_img_container">
							<a href="#"><img src="/resources/images/search.png" alt="검색버튼"></a>
						</div>			
				</div>
				<div class="show_search">

				</div>
				<div class="keyword">
					<ul>
						<li>한강</li>
						<li>한강 노벨</li>
						<li>채식주의자</li>
						<li>노벨문학상</li>
						<li>바람이 분다 가라</li>
					</ul>
				</div>
			</div>
		</div>
		<c:if test="${sessionScope.customer == null && sessionScope.kaccessToken == null && sessionScope.naceessToken == null }">
			<ul class="login_out">  
				<li><a href="${pageContext.request.contextPath }/customer/login"><img src="/resources/images/user.png" alt=""></a></li>
				<li><a class="cart_img" href="${pageContext.request.contextPath }/cart"></a></li>
			</ul>
		</c:if>
		
		<c:if test="${sessionScope.customer != null && empty sessionScope.customer.api}">
			<ul class="login_out">
				<li>${sessionScope.customer.name }</li>
				<li><a href="${pageContext.request.contextPath }/customer/logout">로그아웃</a></li>
				<li><a class="cart_img" href="/cart"></a></li>
			</ul> 
		</c:if>
		
		<c:if test="${sessionScope.customer != null && sessionScope.customer.api eq 'kakao' }">
			<ul class="login_out">
				<li>${sessionScope.customer.name }</li>
				<li><a href="${kakao_logout }">카카오 로그아웃</a></li>
				<li><a class="cart_img" href="/cart"></a></li>
			</ul> 
		</c:if>
		
		<c:if test="${sessionScope.customer != null && sessionScope.customer.api eq 'naver' }">
			<ul class="login_out">
				<li>${sessionScope.customer.name }</li>
				<li><a href="${pageContext.request.contextPath }/naver/logout">네이버 로그아웃</a></li>
				<li><a class="cart_img" href="/cart"></a></li>
			</ul> 
		</c:if>
	</div>
</header>