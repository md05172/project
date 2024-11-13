<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<c:forEach var="item" items="${list }">
		<div>${item.bookid }</div>
		<div>${item.bookname }</div>
		<div>${item.writer }</div>
		<div>${item.price }</div>
		<div>${item.amount }</div>
	</c:forEach>
	
</body>
</html>