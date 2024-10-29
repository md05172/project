<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1" />
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.css"/>
<link rel="stylesheet" href="/resources/css/event.css">
<script type="text/javascript" src="/resources/js/event.js"></script>
<jsp:include page="deco.jsp"></jsp:include>
<title>Insert title here</title>
</head>
<body>
	<div>
		<jsp:include page="header.jsp"></jsp:include>

		<jsp:include page="nav.jsp"></jsp:include>

		<!-- 이벤트 -->
		<div class="slide_wrapper">
			<div class="innder_box">
				<div class="hidden"></div>
				<ul class="slides">
					<li><a href=""><img src="/resources/images/event/event01.jpg" alt=""></a></li>
					<li><a href=""><img src="/resources/images/event/event02.jpg" alt=""></a></li>
					<li><a href=""><img src="/resources/images/event/event03.jpg" alt=""></a></li>
					<li><a href=""><img src="/resources/images/event/event04.jpg" alt=""></a></li>
					<li><a href=""><img src="/resources/images/event/event05.jpg" alt=""></a></li>
				</ul>
				<div class="prev"><i class="fa fa-angle-right" aria-hidden="true"></i></div> 
				<div class="next"><i class="fa fa-angle-right" aria-hidden="true"></i></div>
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