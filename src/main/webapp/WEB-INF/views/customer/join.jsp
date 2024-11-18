<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1" />
<jsp:include page="../deco.jsp"></jsp:include>
<link rel="stylesheet" href="/resources/css/customer/join.css">
<script type="text/javascript" src="/resources/js/join.js"></script>
<title></title>
</head>
<body>
	<div class="container">
		<form method="post">
			<div class="join_logo">
				<a href=""><img src="/resources/images/logo.png" alt="로고"></a>
			</div>
			<div class="info_box">
				<div class="top">
					<h3>회원가입</h3>
				</div>
				<div class="wrang_box">
					<p class="wrang"></p>
				</div>
				<div class="id_box">
					<input type="text" placeholder="사용하실 이메일" name="email">
					<button id="id_check" class="btn" type="button">중복확인</button>
				</div>
				<div class="name_box">
					<input class="name" type="text" placeholder="성함" name="name">
				</div>
				<div class="pw">
					<input type="password" class="pwd" name="password" placeholder="사용하실 비밀번호">
				</div>
				<div class="pw">
					<input type="password" class="pwdcheck" name="checkpw" placeholder="비밀번호 확인">
				</div>
				<div class="ph">
					<input type="tel" name="phone" id="tel" maxlength="13" placeholder="전화번호">
				</div>
				<div class="foot">
					<button id="join" class="btn" type="button">회원가입</button>
					<a id="cancle" href="../"><button class="btn" type="button">취소</button></a>
				</div>
			</div>
		</form>
	</div>
</body>
</html>