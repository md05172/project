<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<header>
	<div class="head_container">
		<!-- 로고 -->
		<div class="logo">
			<a href="#"><img src="/resources/images/logo.png" alt="로고"></a>
		</div>
		
		<!-- 검색 -->
		<div class="search_box">
			<input>
			<div class="search_img_container">
				<a href="#"><img src="/resources/images/search.png" alt="검색버튼"></a>
			</div>
		</div>
		
		<c:if test="${sessionScope.customer == null && sessionScope.kaccessToken == null && sessionScope.naceessToken == null }">
			<ul class="login_out">  
				<li><a href="customer/login">로그인</a></li>
				<li><a href="customer/join">회원가입</a></li>
			</ul>
		</c:if>
		
		<c:if test="${sessionScope.customer != null}">
			<ul class="login_out">
				<li>${sessionScope.customer.name }</li>
				<li><a href="customer/logout">로그아웃</a></li>
			</ul> 
		</c:if>
		
		<c:if test="${sessionScope.kaccessToken != null }">
			<ul class="login_out">
				<li>${sessionScope.api.name }</li>
				<li><a href="${kakao_logout }">카카오 로그아웃</a></li>
			</ul> 
		</c:if>
		
		<c:if test="${sessionScope.naceessToken != null }">
			<ul class="login_out">
				<li>${sessionScope.api.name }</li>
				<li><a href="naver/logout">네이버 로그아웃</a></li>
			</ul> 
		</c:if>
	</div>
</header>