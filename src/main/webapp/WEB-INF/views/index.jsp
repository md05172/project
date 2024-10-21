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
	<h1>index</h1>
	<c:forEach var="item" items="${list }">
		<div>${item.id }</div>
		<div>${item.name }</div>
		<div>${item.publisher }</div>
		<div>${item.price }</div>
		<div>${item.path }</div>
		<div>${item.info }</div>
	</c:forEach>
</body>
</html>