<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>    
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<jsp:include page="../deco.jsp"></jsp:include>
<link rel="stylesheet" href="/resources/css/customer/mypage.css">
<link rel="stylesheet" href="/resources/css/customer/orderList.css">
<title>책모이 - 주문목록</title>
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
				<li><a href="/customer/mypage/order/list" style="color: black; text-decoration: underline;">주문목록</a></li>
				<li><a href="#">취소/반품 내역</a></li>
				<li><a href="/customer/mypage/wish/list">찜 리스트</a></li>
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
			<h2>주문 목록</h2>
		</div>
		<div class="recently_box">
			
			<c:if test="${mypage == null || mypage.size() == 0 }">
				<div class="empty">
					주문한 도서가 없습니다.
				</div>
			</c:if>
			
			<c:forEach var="item" items="${mypage }">
				<c:if test="${item.orders != null}">
					<c:forEach var="orders" items="${item.orders }">
							<c:forEach var="detail" items="${orders.details }">
								<c:if test="${detail.id != null }">
									<div class="order_item">
										<div class="order_item_img_box">
											<div class="item_img_box">
												<img alt="" src="/upload/book/${item.bookPath }">
											</div>
										</div>
										<div class="item_info">
											<div>
												<div class="item_name">
													${item.bookName }
												</div>
												<div class="item_publisher">
													${item.bookPublisher }
												</div>
											</div>
											<div class="item_price">
												<fmt:formatNumber value="${item.bookPrice }" type="Number"/>원
											</div>
											<div class="item_amount">
												${detail.amount }개
											</div>
											<div class="item_date">
												<fmt:formatDate value="${orders.orderdate }" pattern="yyyy-MM-dd"/>
											</div>
										</div>
									</div>	
								</c:if>
							</c:forEach>
					</c:forEach>
				</c:if>
			</c:forEach>
			
			
		</div> <!-- recently_box -->
		
	</div> <!-- right -->
	
</div> <!-- big_container -->
</body>
</html>