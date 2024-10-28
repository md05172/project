<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1" />
<link rel="stylesheet" href="/resources/css/login.css">
<script type="text/javascript" src="/resources/js/login.js"></script>
<title></title>
</head>
<body>
	<div class="container">
		<form method="post">
			<div class="logo">
				<a href="../"><img src="/resources/images/logo.png" alt="로고"></a>
			</div>
			<div class="login_box">
				<div class="wrang_box">
					<p class="wrang"></p>
				</div>
				<c:if test="${sessionScope.error != null }">
					<p class="error" style="color: red; font-size: 0.9em;">${sessionScope.error }</p>
				</c:if>
				<div class="id_box">
					<input type="text" name="email" placeholder="이메일">
				</div>
				<div class="pw_box">
					<input type="password" name="password" placeholder="비밀번호">
				</div>
				<div class="btn_box">
					<button id="login" type="button">로그인</button>
					<a href="join">회원가입</a>
				</div>
				<hr>
				<p>소셜로그인</p>
				<div class="social">
					<a href="${kakao }"><img src="/resources/images/kakao_logo.png" alt="카카오로그인"></a>
					<a href="${naver }"><img src="/resources/images/naver_logo.png" alt="네이버로그인"></a>
				</div>
			</div>
		</form>
		
	</div>
</body>
</html>