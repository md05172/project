<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<jsp:include page="../deco.jsp"></jsp:include>
<link rel="stylesheet" href="/resources/css/customer/mypage.css">
<link rel="stylesheet" href="/resources/css/customer/update.css">
<script type="text/javascript" src="/resources/js/update.js"></script>
<title>책모이 - 회원 수정</title>
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
					<li><a href="/customer/mypage/update" style="color: black; text-decoration: underline;">회원정보 수정</a></li>
					<li><a href="#">회원 탈퇴</a></li>
					<li><a href="#">배송지 관리</a></li>
				</ul>
				<a href="/" style="text-decoration: none; color: black;">메인</a>
			</div>
		</div> <!-- left -->
		
		<div class="right">
			<div>
				<h2>회원 수정</h2>
			</div>
			<div class="update_container">
				<div class="update_box">
					<ul>
						<li>회원님의 정보를 안전하게 보호하기 위해 비밀번호를 다시 한번 입력하시기 바랍니다.</li>
						<li>항상 비밀번호는 타인에게 노출되지 않게 관리하시기 바랍니다.</li>
					</ul>
					<div>
						<table>
							<tr>
								<th>이메일</th>
								<td>${sessionScope.customer.email }</td>
							</tr>
							<tr>
								<th>비밀번호</th>
								<td><input type="password" class="password"><span class="check"></span></td>
							</tr>
						</table>
					</div>
					<div class="btnn_group">
						<span><button class="ok">확인</button></span>
						<span><a href="/customer/mypage"><button>취소</button></a></span>
					</div>
				</div>
				
				<div class="update_cust_box hide">
					<table>
						<tr>
							<th>이름</th>
							<td>${sessionScope.customer.name }</td>
						</tr>
						<tr>
							<th>이메일</th>
							<td>${sessionScope.customer.email }</td>
						</tr>
						<tr>
							<th>전화번호</th>
							<td><input type="text" value="${sessionScope.customer.phone }" disabled></td>
						</tr>
						<tr>
							<th>변경할 비밀번호</th>
							<td><input type="password" name="password"></td>
						</tr>
						<tr>
							<th>비밀번호 확인</th>
							<td><input type="password" name="repassword"></td>
						</tr>
						<tr>
							<td colspan="2" class="bt"><button class="update">변경</button></td>
						</tr>
					</table>
				</div>
				
			</div>
			
		</div> <!-- right -->
		
	</div> <!-- big_container -->
</body>
</html>