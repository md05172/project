<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<jsp:include page="../deco.jsp"></jsp:include>
<link rel="stylesheet" href="/resources/css/book/detail.css">
<title>Insert title here</title>
</head>
<body>
	<jsp:include page="../header.jsp"></jsp:include>
	<jsp:include page="../nav.jsp"></jsp:include>
	<div class="main_container">
		<div class="detail_container">
			<div class="left_box">
				<div class="inner_box">
					<div class="img_box">
						<img alt="" src="/upload/book/${item.path }">
					</div>
				</div>
			</div>	
			
			<div class="right_box">
				<p class="name">${item.name }</p>
				<span class="writer">${item.writer }</span>
				<span class="publisher">${item.publisher }</span>
				
				<div class="price_box">
					<span class="price">${item.price }</span>
					<span class="won">원</span>
				</div>
				
				<div class="buycart_box">
					<div class="amount_box">
						<div class="amount">
							<div class="plus_minus">
								<button>-</button>
								<input type="text" value="1">
								<button>+</button>
							</div>
							<div class="cart_price_box">
								<span>${item.price }</span>
								<span>원</span>
							</div>
						</div>
						<div class="btn_box">
							<button class="cart">장바구니</button>
							<button class="buy">구매하기</button>
						</div>
					</div>
				</div>
			</div>
		</div>
		
		<div class="detail_nav_list">
			<div class="detail_nav">
				<div class="nav_box">
					<ul class="nav">
						<li><a href="#writer_info">작가 소개</a></li>
						<li><a href="#info">책 소개</a></li>
						<li><a href="#review">리뷰</a></li>
					</ul>
				</div>
			</div>
		</div>
		
		<div class="book_detail_box">
			<div class="book_info">
				${item.info }
			</div>
			<div class="writer_info" id="writer_inf">
				${item.writerInfo }
			</div>
		</div>
	</div>
</body>
</html>