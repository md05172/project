<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>    
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>    
<!DOCTYPE html>
<html>
<head>
<jsp:include page="deco.jsp"></jsp:include>
<link rel="stylesheet" href="/resources/css/success.css">
<meta charset="UTF-8">
<title></title>
</head>
<body>
	<div class="big_container">
		<div>
			<h2><span class="title">주문이 정상적으로 완료</span> 되었습니다.</h2> 
		</div>
		<div class="info_box">
			<table>
				<caption>주문 상품 <a href="/"><button>홈으로</button></a></caption>
				<thead>
					<tr>
						<th></th>
						<th>상품명</th>
						<th>수량</th>
						<th>할인금액</th>
						<th>총 결제금액</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="item" items="${bookList }">
						<tr>
							<td><img alt="" src="/upload/book/${item.path }"></td>
							<td>${item.name }</td>
							<td>${item.count }</td>
							<td class="sale">₩0</td>
							<td class="total">₩<fmt:formatNumber value="${item.price * item.count }" type="Number"/></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
		
		<div class="pay_info">
			<table>
				<caption>결제 정보</caption>
				<tr>
					<th>상품금액</th>
					<td>₩<fmt:formatNumber value="${amount }" type="Number"/></td>
				</tr>
				<tr>
					<th>할인금액</th>
					<td class="sale">₩0</td>
				</tr>
				<tr>
					<th>배송비</th>
					<td>₩0</td>
				</tr>
				<tr>
					<th>총 결제금액</th>
					<td class="total">₩<fmt:formatNumber value="${amount }" type="Number"/></td>
				</tr>
			</table>
		</div>
		
		<div class="info_wrap">
			<div class="left_box">
				<table>
					<caption>주문자 정보</caption>
					<tr>
						<th>주문번호</th>
						<td>${orderId }</td>
					</tr>
					<tr>
						<th>주문일</th>
						<td>
							<c:set var="now" value="<%=new java.util.Date()%>" />
							<c:set var="sysYear"><fmt:formatDate value="${now}" pattern="yyyy.MM.dd"/></c:set>
							${sysYear }
						</td>
					</tr>
					<tr>
						<th>이름</th>
						<td>${sessionScope.customer.name }</td>
					</tr>
					<tr>
						<th>이메일</th>
						<td>${sessionScope.customer.email }</td>
					</tr>
				</table>
			</div>
			<div class="right_box">
				<table>
					<caption>배송지 정보</caption>
					<tr>
						<th>이름</th>
						<td>${sessionScope.customer.name }</td>
					</tr>
					<tr>
						<th>배송지 주소</th>
						<td>
							<p><span>(${address.postcode })</span><span>${address.roadaddress }</span></p>
							<p><span>${address.jibunaddress }</span><span>${address.detailaddress }</span></p>
							<p>${address.extraaddress }</p>
						</td>
					</tr>
					<tr>
						<th>이름</th>
						<td>${sessionScope.customer.name }</td>
					</tr>
					<tr>
						<th>이메일</th>
						<td>${sessionScope.customer.email }</td>
					</tr>
				</table>
			</div>
		</div>
		
	</div>
	
</body>
</html>