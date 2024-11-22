<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<jsp:include page="../deco.jsp"></jsp:include>
<title>책모이 - 마이페이지</title>
</head>
<body>
	찜
	<c:forEach var="wish" items="${sessionScope.customer.wish }">
		<div>${wish.bookId }</div>
	</c:forEach>
	
	주문
	<c:forEach var="order" items="${sessionScope.customer.orders }">
		<div>${order.id }</div>
		<div>${order.addressId }</div>
		<div>${order.orderdate }</div>
		<div>${order.code }</div>
		<c:forEach var="detail" items="${order.details }">
			<div>${detail.id }</div>		
			<div>${detail.bookId }</div>		
			<div>${detail.amount }</div>		
			<div>${detail.phone }</div>		
		</c:forEach>
	</c:forEach>
</body>
</html>