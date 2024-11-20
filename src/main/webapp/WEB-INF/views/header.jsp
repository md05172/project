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
		<c:if test="${sessionScope.customer == null && sessionScope.customer.api == null && sessionScope.customer.api == null }">
			<ul class="login_out">  
				<li class="hover">
					<span class="user_img_box"><img src="/resources/images/user.png" alt=""></span>
					<div class="hidden_box">
						<div class="hidden_box_inner_wrap">
							<div class="hidden_box_inner">
								<p><a href="/customer/login">로그인</a></p>
								<p><a href="/customer/join">회원가입</a></p>
							</div>
						</div>
					</div>	
				</li>
				<li><a class="cart_img" href="/cart"></a></li>
			</ul>
		</c:if>
		
		<c:if test="${sessionScope.customer != null && sessionScope.customer.api == null}">
			<ul class="login_out">
				<li class="hover">
					<span class="user_img_box"><img src="/resources/images/user.png" alt=""></span>
					<div class="hidden_box">
						<div class="hidden_box_inner_wrap">
							<div class="hidden_box_inner">
								<p>${sessionScope.customer.name }님</p>
								<p><a>마이페이지</a></p>
								<p><a href="/customer/logout">로그아웃</a></p>
							</div>
						</div>
					</div>	
				</li>
				<li><a class="cart_img" href="/cart"></a></li>
			</ul> 
		</c:if>
		
		<c:if test="${sessionScope.customer != null && sessionScope.customer.api == 'kakao' }">
			<ul class="login_out">
				<li class="hover">
					<span class="user_img_box"><img src="/resources/images/user.png" alt=""></span>
					<div class="hidden_box">
						<div class="hidden_box_inner_wrap">
							<div class="hidden_box_inner">
								<p>${sessionScope.customer.name }님</p>
								<p><a>마이페이지</a></p>
								<p><a href="/kakao/logout">로그아웃</a></p>
							</div>
						</div>
					</div>	
				</li>
				<li><a class="cart_img" href="/cart"></a></li>
			</ul> 
		</c:if>
		
		<c:if test="${sessionScope.customer != null && sessionScope.customer.api == 'naver' }">
			<ul class="login_out">
				<li class="hover">
					<span class="user_img_box"><img src="/resources/images/user.png" alt=""></span>
					<div class="hidden_box">
						<div class="hidden_box_inner_wrap">
							<div class="hidden_box_inner">
								<p>${sessionScope.customer.name }님</p>
								<p><a>마이페이지</a></p>
								<p><a href="/naver/logout">로그아웃</a></p>
							</div>
						</div>
					</div>	
				</li>
				<li><a class="cart_img" href="/cart"></a></li>
			</ul> 
		</c:if>
	</div>
</header>