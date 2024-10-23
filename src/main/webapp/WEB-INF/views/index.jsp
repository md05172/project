<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div>
		<header>
			<!-- 로고 -->
			<div>
				<a href="#"><img src="" alt=""></a>
			</div>
			
			<!-- 검색 -->
			<div>
				<input>
				<div>
					<a href="#"><img src="" alt=""></a>
				</div>
			</div>
			
			<c:if test="${sessionScope.accessToken == null && sessionScope.kaccessToken == null && sessionScope.naceessToken == null }">
				<ul>  
					<li><a href="member/login">로그인</a></li>
					<li><a href="member/join">회원가입</a></li>
				</ul>
			</c:if>
			
			<c:if test="${sessionScope.kaccessToken != null }">
				<a href="#">카카오 로그아웃</a>
			</c:if>
			
			<c:if test="${sessionScope.naccessToken != null }">
				<a href="#">네이버 로그아웃</a>
			</c:if>
		</header>
		
		<!-- 메뉴 -->
		<nav>
			<ul>
				<li><a href="">베스트</a></li>
				<li><a href="book/list">국내도서</a></li>
				<li><a href="">외국도서</a></li>
				<li><a href="">이벤트</a></li>
				<li><a href="">고객센터</a></li>
				<li><a href="book/dummy">더미데이터</a></li>
				<li><a href="book/init">초기화</a></li>
			</ul>
		</nav>   
		
		<!-- 이벤트 -->
		<div>
			<div>
				<div>
					<img alt="" src="">
				</div>
				
				<div>
					<img alt="" src="">
				</div>
				
				<div>
					<img alt="" src="">
				</div>
				
				<div>
					<img alt="" src="">
				</div>
				
				<div>
					<img alt="" src="">
				</div>
			</div>
		</div>
		
		<!-- 베스트 책 5개 -->
		<c:forEach var="item" items="${list }">
			<div>${item.id }</div>
			<div>${item.name }</div>
			<div>${item.publisher }</div>
			<div>${item.price }</div>
			<div>${item.path }</div>
			<div>${item.info }</div>
	</c:forEach>
		
	</div>
</body>
</html>