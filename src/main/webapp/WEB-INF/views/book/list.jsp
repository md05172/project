<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1" />
<jsp:include page="../deco.jsp"></jsp:include>
<link rel="stylesheet" href="/resources/css/book/list.css">
<title></title>
</head>
<body>
	<jsp:include page="../header.jsp"></jsp:include>
	<jsp:include page="../nav.jsp"></jsp:include>
	
	<section>
	<div class="sidemenu_box">
		<ul class="sidemenu">
			<li>사이드메뉴</li>
			<li>사이드메뉴</li>
			<li>사이드메뉴</li>
		</ul>
	</div>
	<ul class="item_list">
		<c:forEach var="item" items="${list }">
			<li>
				<div class="item">
					<div class="item_img">
						<div class="img_box">
							<a href="#"><img src="/upload/book/${item.path }"></a>
						</div>
					</div>
					<div class="item_info">
						<p class="item_name">${item.name }</p>
						<span class="item_publisher">${item.publisher }</span>
						<span class="item_writer">${item.writer }</span>
						<p class="item_price">${item.price }원</p>
						<p class="item_writerInfo">${item.writerInfo }</p>
						<p class="info">${item.info }</p>
					</div>
					<div class="buycart">
						<div class="heart">
							<i class="fa fa-heart-o" aria-hidden="true"></i>
						</div>
						<div>
							<button>장바구니</button>
							<button>구매하기</button>
						</div>
					</div>
				</div>
			</li>
		</c:forEach>
	</ul>
	</section>
	<div><a href="?page=1">처음</a></div>
	<div><a href="?page=${pager.prev }">이전</a></div>
	<c:forEach var="page" items="${pager.list }">
		<div><a href="?page=${page }">${page }</a></div>
	
	</c:forEach>
	<div><a href="?page=${pager.next }">다음</a></div>
	<div><a href="?page=${pager.last }">마지막</a></div>
	
	
</body>
</html>