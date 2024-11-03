<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
</head>
<body>	
	<h1>장바구니</h1>
	
	<c:if test="${list.size() < 1 }">
		<div>비어있음</div>
	</c:if>
	
	<c:forEach var="item" items="${list }">
		<div>${item.id }</div>
		<div>${item.name }</div>
		<div>${item.publisher }</div>
		<div>${item.price }</div>
		<div>${sessionScope.cart.cart[item.id] }</div>
	</c:forEach>
</body>
</html>