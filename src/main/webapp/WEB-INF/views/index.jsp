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
						<li><a href=""><img src="/resources/images/event/event01.jpg" alt=""></a></li>
						<li><a href=""><img src="/resources/images/event/event02.jpg" alt=""></a></li>
						<li><a href=""><img src="/resources/images/event/event03.jpg" alt=""></a></li>
						<li><a href=""><img src="/resources/images/event/event04.jpg" alt=""></a></li>
						<li><a href=""><img src="/resources/images/event/event05.jpg" alt=""></a></li>
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
								<a>
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
								<a>
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
					</div>
				</div>
			</div>
		</div>
		
		<footer class="footer">
			<div class="footer__wrap">
				<nav class="footer__nav">
					<ul class="footer__nav-list">
						<li class="footer__nav-item"><a class="footer__nav-anchor" href="/daejeon/content.do?menu=5449">찾아오시는 길</a></li>
						<li class="footer__nav-item"><a class="footer__nav-anchor" href="/daejeon/content.do?menu=5442">전화번호안내</a></li>
							<li class="footer__nav-item"><a class="footer__nav-anchor" href="/content.do?menu=1685" title="새창" target="blank">이메일무단수집거부</a></li>
							<li class="footer__nav-item"><a class="footer__nav-anchor" href="/daejeon/content.do?menu=5452" style="font-size: 1.1em;font-weight: bold;color: white;">개인정보처리방침</a></li>
						<li class="footer__nav-item"><a class="footer__nav-anchor" href="/board.do?menu=137" title="새창" target="blank">홈페이지오류신고</a></li>
						<li class="footer__nav-item"><a class="footer__nav-anchor" href="/board.do?menu=135" title="새창" target="blank">민원광장</a></li>
					</ul>
					<ul class="footer__public-list">
						<li class="footer__public-item"><a class="footer__public-anchor" href="/content.do?menu=127">경영공시</a></li>
						<li class="footer__public-item"><a class="footer__public-anchor" href="http://www.academyinfo.go.kr/popup/pubinfo1690/list.do?schlId=0000603" title="새창" target="blank">대학정보공시</a></li>
					</ul>
				</nav>
				<div class="footer__info-wrap">
					<div class="footer__info">
						<address class="footer__info-address">
						[34503] 대전광역시 동구 우암로 352-21
							&nbsp;&nbsp;<span style="font-size: 1.1em;font-weight: bold;color: white;">TEL 042-670-0600</span>
						&nbsp;&nbsp;FAX 042-670-0519<br>
							<small class="footer__info-copy">COPYRIGHT 2010 BY KOREA POLYTECHNICS. ALL RIGHTS RESERVED.</small>
						</address>
						<p class="footer__info-small">본 웹사이트에 게시된 이메일 주소가 전자우편 수집 프로그램이나 그밖의 기술적 장치를 이용하여 무단으로 수집되는 것을 거부하며, 이를 위반시 정보통신망법에 의해 형사처벌 됨을 유념하시기 바랍니다.</p>
					</div>
				</div>
			</div>
		</footer>
	</div>
</body>
</html>