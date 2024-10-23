<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
</head>
<body>
	<div class="form-group address-group">
		<label for="sample4_postcode">우편번호</label> 
		<input type="text"id="sample4_postcode" placeholder="우편번호"> 
		<input type="button" class="find" value="우편번호 찾기">
	</div>
	<div class="form-group address-group">
		<label for="sample4_roadAddress">도로명주소</label>
		<input type="text" id="sample4_roadAddress" placeholder="도로명주소">
	</div>
	<div class="form-group address-group">
		<label for="sample4_jibunAddress">지번주소</label> 
		<input type="text" id="sample4_jibunAddress" placeholder="지번주소">
	</div>
	<div class="form-group address-group">
		<label for="sample4_detailAddress">상세주소</label> 
		<input type="text" id="sample4_detailAddress" placeholder="상세주소"> 
		<input type="text" id="sample4_extraAddress" placeholder="참고항목">
	</div>
</body>
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script type="text/javascript" src="/resources/js/daumAddress.js"></script>
</html>