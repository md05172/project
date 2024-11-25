<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<jsp:include page="../deco.jsp"></jsp:include>
<link rel="stylesheet" href="/resources/css/customer/mypage.css">
<link rel="stylesheet" href="/resources/css/customer/wishList.css">
<script type="text/javascript" src="/resources/js/wishList.js"></script>
</head>
<body>
<div class="big_container">
		<div class="left">
			<div>
				<h2><a href="/customer/mypage">마이 페이지</a></h2>
			</div>		
			<div>
				<h3>쇼핑정보</h3>
				<ul>
					<li><a href="/customer/mypage/order/list">주문목록</a></li>
					<li><a href="#">취소/반품 내역</a></li>
					<li><a href="/customer/mypage/wish/list" style="color: black; text-decoration: underline;">찜 리스트</a></li>
				</ul>
			</div>
			
			<div>
				<h3>혜택관리</h3>
				<ul>
					<li><a href="#">쿠폰</a></li>
					<li><a href="#">적립금</a></li>
				</ul>
			</div>
			
			<div>
				<h3>회원정보</h3>
				<ul>
					<li><a href="/customer/mypage/update">회원정보 수정</a></li>
					<li><a href="#">회원 탈퇴</a></li>
					<li><a href="#">배송지 관리</a></li>
				</ul>
				<a href="/" style="text-decoration: none; color: black;">메인</a>
			</div>
		</div> <!-- left -->
		
		<div class="right">
			<div class="head">
				<h2>찜 목록</h2>
			</div>
			
			<c:if test="${mypage == null || mypage.size() == 0 }">
				<div class="empty">
					찜한 도서가 없습니다.
				</div>
			</c:if>
			
			<div class="recently_box">
				<div class="flex-wrap">
					<c:forEach var="item" items="${mypage }">
						<c:if test="${item.wishs != null }">
							<c:forEach var="wish" items="${item.wishs }">
								<c:if test="${wish.id != null }">
									<div class="wish_item_box">
										<div class="wish_img_box">
											<img alt="" src="/upload/book/${item.bookPath }">
										</div>
										<div class="wish_item_info">
											<p>${item.bookName }</p>
											<p>${item.bookPublisher } 
												<img class="wish" data-wishid="${wish.id }" alt="" src="/resources/images/fullheart.png">
											</p>
										</div>
									</div>
								</c:if>
							</c:forEach>
						</c:if>
					</c:forEach>
				</div>
							
			</div> <!-- recently_box -->
			
		</div> <!-- right -->
		
	</div> <!-- big_container -->
</body>
</html>