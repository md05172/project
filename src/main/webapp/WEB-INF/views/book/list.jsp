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

	<c:forEach var="item" items="${list }">
		<div>${item.name }</div>
		<div>${item.publisher }</div>
		<div>${item.price }</div>
		<div>${item.writer }</div>
		<div>${item.info }</div>
		<div>${item.writerInfo }</div>
		<hr>
	</c:forEach>
	<div><a href="?page=1">처음</a></div>
	<div><a href="?page=${pager.prev }">이전</a></div>
	<c:forEach var="page" items="${pager.list }">
		<div><a href="?page=${page }">${page }</a></div>
	
	</c:forEach>
	<div><a href="?page=${pager.next }">다음</a></div>
	<div><a href="?page=${pager.last }">마지막</a></div>
	
	
</body>
</html>