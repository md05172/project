<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
<link rel="stylesheet" href="/resources/css/join.css">
<script type="text/javascript" src="/resources/js/j	oin.js"></script>
</head>
<body>
	<div class="container">
		<form method="post">
			<div class="logo">
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
				<div class="form-group address-group address_box">
					<input type="number" name="address.postcode" id="sample4_postcode" placeholder="우편번호" readonly="readonly">
					<input type="button" class="find" value="우편번호 찾기">
				</div>
				<div class="form-group address-group ad">
					<input type="text" name="address.roadaddress" id="sample4_roadAddress" placeholder="도로명주소">
				</div>
				<div class="form-group address-group ad">
					<input type="text" name="address.jibunaddress" id="sample4_jibunAddress" placeholder="지번주소">
				</div>
				<div class="form-group address-group ad">
					<input type="text" name="address.detailaddress" id="sample4_detailAddress" placeholder="상세주소">
					<input type="text" name="address.extraaddress" id="sample4_extraAddress" placeholder="참고항목">
				</div>
				<div class="foot">
					<button id="join" type="button">회원가입</button>
					<a id="cancle" href="../"><button type="button">취소</button></a>
				</div>
			</div>
		</form>
	</div>
</body>
<script
	src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script type="text/javascript" src="/resources/js/daumAddress.js"></script>
</html>