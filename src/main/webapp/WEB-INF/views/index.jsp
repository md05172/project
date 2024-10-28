<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1" />
<jsp:include page="deco.jsp"></jsp:include>
<title>Insert title here</title>
</head>
<body>
	<div>
		<jsp:include page="header.jsp"></jsp:include>
		
		<jsp:include page="nav.jsp"></jsp:include>
		
		<!-- 이벤트 -->
		<div>
			<div>
				<div>
					<img alt="" src="">
				</div>
				
				<div>
					<img alt="" src="">
				</div>
				
				<div>
					<img alt="" src="">
				</div>
				
				<div>
					<img alt="" src="">
				</div>
				
				<div>
					<img alt="" src="">
				</div>
			</div>
		</div>
		
		<!-- 베스트 책 5개 -->
		<c:forEach var="item" items="${list }">
			<div>${item.id }</div>
			<div>${item.name }</div>
			<div>${item.publisher }</div>
			<div>${item.price }</div>
			<div>${item.path }</div>
			<div>${item.info }</div>
		</c:forEach>
		
	</div>
</body>
</html>