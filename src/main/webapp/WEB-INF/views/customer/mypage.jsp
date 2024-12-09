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
					<li><a href="/customer/mypage/order/list">주문목록</a></li>
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
			<div class="mypage_top_box">
				<div class="top_inner_box">
					<div class=inner_box_left>
						<div class="grade_box">
							<div class="wd">
								<span class="name font">${sessionScope.customer.name }</span> 님은 <span class="grade font">기본등급</span>입니다.
							</div>
							<div class="grade_button_box">
								<button>등급혜택 안내</button>
							</div>
						</div>
						<div class="grade_box">
							<div class="buy_info_box wd">
								<span>구매금액 <b><fmt:formatNumber value="${orderSum }" type="Number"/></b>원</span><span>구매횟수 <b>${orderCount }</b>건</span>
							</div>
							<div class="update_customer_box">
								<a href="/customer/mypage/update"><button>회원정보 수정</button></a>
							</div>
						</div>
					</div> <!-- inner_box_left -->
					
					<div class="line"></div>
					
					<div class="inner_box_right">
						<p>사용가능 적립금 <span><span class="font">0</span>적립금</span></p>
						<p>보유중인 쿠폰 <span><span class="font">0</span>건</span></p>
						<p>찜리스트 <span><a class="wi" href="/customer/mypage/wish/list"><span class="font">${wishCount }</span></a>개</span></p>
					</div>
				</div>
			</div> <!-- mypage_top_box -->
			
			<div class="guide_line"></div>
			
			<div class="recently_box">
				<div class="head">
					<span>주문 현황</span> <span>최근 3개월 기준</span>
				</div>
				<div class="wrap">
					<div class="recently">
						<div class="recently_info">
							<div class="column"><p>입금완료</p><p><span class="cnt">${orderCount }</span>건</p></div>
							<div class="column"><p>배송중</p><p><span class="cnt">${orderCount }</span>건</p></div>
							<div class="column"><p>주문도서수</p><p><span class="cnt">${orderBookCount }</span>개</p></div>
						</div>
					</div>
					<div class="cancle">
						<div><p>취소/반품 내역</p><p><span class="cnt">0</span>건</p></div>
					</div>
				</div> <!-- top_inner_box -->
			</div> <!-- recently_box -->
			
			<div class="page_wrap">
				<div class="page_item">
					<div class="top">
						<p>1:1문의</p>
						<p>더보기</p>
					</div>
					<div class="body">
						<div class="body_item">
							<p>답변대기 <span><span class="cnt">0</span>건</span></p>
						</div>
						<div class="body_item">
							<p>답변대기 <span><span class="cnt">0</span>건</span></p>
						</div>
						<div class="body_button">
							<button>1:1문의하기</button>
						</div>
					</div>
				</div>
				
				<div class="page_item">
					<div class="top">
						<p>상품문의</p>
						<p>더보기</p>
					</div>
					<div class="body">
						<div class="body_item">
							<p>답변대기 <span><span class="cnt">0</span>건</span></p>
						</div>
						<div class="body_item">
							<p>답변대기 <span><span class="cnt">0</span>건</span></p>
						</div>
						<div class="body_button">
							<button>상품문의하기</button>
						</div>
					</div>
				</div>
				
				<div class="page_item">
					<div class="top">
						<p>리뷰</p>
						<p>더보기</p>
					</div>
					<div class="body">
						<div class="body_item">
							<p>리뷰작성 <span><span class="cnt">${reviewCount }</span>건</span></p>
						</div>
						<div class="body_item">
							<p>달린댓글 <span><span class="cnt">0</span>건</span></p>
						</div>
						<div class="body_button">
							<button>리뷰관리</button>
						</div>
					</div>
				</div>
			</div>
			
		</div> <!-- right -->
		
	</div> <!-- big_container -->

</body>
</html>