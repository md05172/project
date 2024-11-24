<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<jsp:include page="../deco.jsp"></jsp:include>
<link rel="stylesheet" href="/resources/css/customer/mypage.css">
<title>책모이 - 마이페이지</title>
</head>
<body>
	<div class="big_container">
		<div class="left">
			<div>
				<h2>마이 페이지</h2>
			</div>		
			<div>
				<h3>쇼핑정보</h3>
				<ul>
					<li>주문목록</li>
					<li>취소/반품 내역</li>
					<li>찜 리스트</li>
				</ul>
			</div>
			
			<div>
				<h3>혜택관리</h3>
				<ul>
					<li>쿠폰</li>
					<li>적립금</li>
				</ul>
			</div>
			
			<div>
				<h3>회원정보</h3>
				<ul>
					<li>회원정보 수정</li>
					<li>회원 탈퇴</li>
					<li>배송지 관리</li>
				</ul>
			</div>
		</div> <!-- left -->
		
		<div class="right">
			<div class="mypage_top_box">
				<div class="top_inner_box">
					<div class=inner_box_left>
						<div class="grade_box">
							<div>
								<span class="name font">${sessionScope.customer.name }</span> 님은 <span class="grade font">기본등급</span>입니다.
							</div>
							<div class="grade_button_box">
								<button>등급혜택 안내</button>
							</div>
						</div>
						<div class="grade_box">
							<div class="buy_info_box">
								<span>구매금액 <b>0</b>원</span><span>구매횟수 <b>0</b>건</span>
							</div>
							<div class="update_customer_box">
								<button>회원정보 수정</button>
							</div>
						</div>
					</div> <!-- inner_box_left -->
					
					<div class="line"></div>
					
					<div class="inner_box_right">
						<p>사용가능 적립금 <span><span class="font">0</span>적립금</span></p>
						<p>보유중인 쿠폰 <span><span class="font">0</span>건</span></p>
						<p>찜리스트 <span><span class="font">0</span>개</span></p>
					</div>
				</div>
			</div>
			
			<div class="guide_line"></div>
			
			<div class="recently_box">
				<div class="head">
					<span>주문 현황</span> <span>최근 3개월 기준</span>
				</div>
				<div class="wrap">
					<div class="recently">
						<div class="recently_info">
							<div class="column"><p>입금완료</p><p><span class="cnt">0</span>건</p></div>
							<div class="column"><p>배송중</p><p><span class="cnt">0</span>건</p></div>
							<div class="column"><p>도서수</p><p><span class="cnt">0</span>개</p></div>
						</div>
					</div>
					<div class="cancle">
						<div><p>취소/반품 내역</p><p><span class="cnt">0</span>건</p></div>
					</div>
				</div>
			</div>
			
		</div> <!-- right -->
		
		
	</div> <!-- big_container -->

</body>
</html>