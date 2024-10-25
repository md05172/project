<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
<link rel="stylesheet" href="/resources/css/join.css">
</head>
<body>
	<div class="container">
		<form action="">
			<div class="logo">
				<a href=""><img src="/resources/images/logo.png" alt="로고"></a>
			</div>
			<div class="info_box">
				<div class="top">
					<h3>회원가입</h3>
				</div>
				<div class="id_box">
					<input type="text" placeholder="사용하실 아이디">
					<button class="btn">중복확인</button>
				</div>
				<div class="pw">
					<input type="text" placeholder="사용하실 비밀번호">
				</div>
				<div class="pw">
					<input type="text" placeholder="비밀번호 확인">
				</div>
				<div class="form-group address-group address_box">
					<input type="text" id="sample4_postcode" placeholder="우편번호">
					<input type="button" class="find" value="우편번호 찾기">
				</div>
				<div class="form-group address-group ad">
					<input type="text" id="sample4_roadAddress" placeholder="도로명주소">
				</div>
				<div class="form-group address-group ad">
					<input type="text" id="sample4_jibunAddress" placeholder="지번주소">
				</div>
				<div class="form-group address-group ad">
					<input type="text" id="sample4_detailAddress" placeholder="상세주소">
					<input type="text" id="sample4_extraAddress" placeholder="참고항목">
				</div>
				<div class="foot">
					<button>회원가입</button>
					<button>취소</button>
				</div>
			</div>
		</form>
	</div>
</body>
<script
	src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script type="text/javascript" src="/resources/js/daumAddress.js"></script>
</html>