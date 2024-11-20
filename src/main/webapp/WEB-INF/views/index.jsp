<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1" />
<link rel="stylesheet" href="/resources/css/event.css">
<link rel="stylesheet" href="/resources/css/index.css">
<script type="text/javascript" src="/resources/js/event.js"></script>
<script type="text/javascript" src="/resources/js/index.js"></script>
<jsp:include page="deco.jsp"></jsp:include>
<title>Insert title here</title>
</head>
<body>
	<div class="big_container">
		<jsp:include page="header.jsp"></jsp:include>

		<jsp:include page="nav.jsp"></jsp:include>

		<!-- 이벤트 -->
		<div class="event">
			<div class="hidden"></div>
			<div class="slide_wrapper">
				<div class="innder_box">
					<ul class="slides">
						<li><a><img src="/resources/images/event/event01.jpg" alt=""></a></li>
						<li><a><img src="/resources/images/event/event02.jpg" alt=""></a></li>
						<li><a><img src="/resources/images/event/event03.jpg" alt=""></a></li>
						<li><a><img src="/resources/images/event/event04.jpg" alt=""></a></li>
						<li><a><img src="/resources/images/event/event05.jpg" alt=""></a></li>
						<li><a><img src="/resources/images/event/event06.jpg" alt=""></a></li>
						<li><a><img src="/resources/images/event/event07.jpg" alt=""></a></li>
					</ul>
				</div>
					<div class="prev"><i class="fa fa-angle-right" aria-hidden="true"></i></div> 
					<div class="next"><i class="fa fa-angle-right" aria-hidden="true"></i></div>
			</div>
		</div>
	
		<!-- 한강 노벨문학상  -->
		<div class="best_container">
			<div class="best_inner">
				<div class="best_name">
					<h2>작가 한강 노벨문학상!🏆</h2>
				</div>
				
				<div class="best_box">
					<c:forEach var="item" items="${novel }">
						<div class="best_item_box">
							<div class="best_img_box">
								<a href="/book/detail/${item.id }">
									<img alt="" src="/upload/book/${item.path }">
								</a>
							</div>
							<div class="name_box">
								<p class="best_name">${item.name }</p>
								<p class="best_writer">${item.writer }</p>
							</div>
						</div>
					</c:forEach>
				</div> 
			</div>
		</div> <!-- 노벨 끝 -->
	
	
		<!-- 베스트 책 5개 -->
		<div class="best_container">
			<div class="best_inner">
				<div class="best_name">
					<h2 id="slide">베스트🌟도서</h2>
				</div>
				
				<div class="best_box">
					<c:forEach var="item" items="${best }">
						<div class="best_item_box">
							<div class="best_img_box">
								<a href="/book/detail/${item.id }">
									<img alt="" src="/upload/book/${item.path }">
								</a>
							</div>
							<div class="name_box">
								<p class="best_name">${item.name }</p>
								<p class="best_writer">${item.writer }</p>
							</div>
						</div>
					</c:forEach>
				</div>
			</div>
		</div> <!-- 베스트 끝 -->
		
		<!-- 이달 도서 -->
		<div class="this_month">
			<div class="best_inner">
				<div class="best_name">
					<h2>이달의 주목도서</h2>
				</div>
				
				<div class="month_box">
					<div class="ko_box">
						<p><span>국내도서</span><a href="book/list/ko"><span><img class="down" src="/resources/images/formkit_down.svg">더보기</span></a></p>
						<div class="ko">
							<c:forEach var="ko" items="${koList }">
								<div class="ko_img_box">
									<img alt="" src="/upload/book/${ko.path }">
								</div>
							</c:forEach>
						</div>
					</div>
					
					<div class="nko_box">
						<p><span>외국도서</span><a href="book/list/nko"><span><img class="down" src="/resources/images/formkit_down.svg">더보기</span></a></p>
						<div class="nko">
							<c:forEach var="ko" items="${nkoList }">
								<div class="nko_img_box">
									<img alt="" src="/upload/book/${ko.path }">
								</div>
							</c:forEach>
						</div>
					</div> <!-- 외국도서 -->
					
				</div> <!-- 이달의 주목도서 box -->
			</div> <!-- 베스트도서 끝 -->
		</div> <!-- 이달의 주목도서 -->
	</div> <!-- big_container -->
	<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>