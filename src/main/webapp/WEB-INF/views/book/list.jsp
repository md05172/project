<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>    
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1" />
<jsp:include page="../deco.jsp"></jsp:include>
<link rel="stylesheet" href="/resources/css/book/list.css">
<script type="text/javascript" src="/resources/js/list.js"></script>
<title></title>
</head>
<body>
	<jsp:include page="../header.jsp"></jsp:include>
	<jsp:include page="../nav.jsp"></jsp:include>
	
	<section>
	<div class="sidemenu_box">
		<h1>베스트셀러</h1>
		<ul class="sidemenu">
			<li>종합 베스트</li>
			<li class="menu_check">주간</li>
			<li>월간</li>
			<li>연간</li>
		</ul>
		
		<ul class="sidemenu">
			<li>온라인 베스트</li>
			<li>주간</li>
			<li>월간</li>
			<li>연간</li>
		</ul>
	</div>
	<div class="container_box">
		<div class="all_box">
			<label>
				<input class="all_check" type="checkbox">
				전체선택
			</label>
			<button class="all_cart">장바구니</button>
		</div>
		<ul class="item_list">
			
			<c:forEach var="item" items="${list }">
				<li>
					<div class="item">
						<div class="wrap">
							<div class="check">
								<input class="check_box" data-bookid="${item.id }" type="checkbox" name="check">
							</div>
							<div class="item_img">
								<div class="img_box">
									<a href="../detail/${item.id }"><img src="/upload/book/${item.path }"></a>
								</div>
							</div>
							<div class="item_info">
								<p class="item_name"><a href="../detail/${item.id }">${item.name }</a></p>
								<span class="item_publisher">${item.publisher }</span>
								<span class="item_writer">${item.writer }</span>
								<p class="item_price">${item.price }원</p>
								<p class="item_writerInfo">${item.writerInfo }</p>
								<p class="info">${item.info }</p>
								<div class="review">
									<img alt="" src="/resources/images/full_star.png"> 
									<span class="review_text">
										<c:if test="${item.avg > 0 }">
											<fmt:formatNumber value="${item.avg }" pattern=".00" />
										</c:if>
										<c:if test="${item.avg == null }">
											0
										</c:if>
									</span>
									<span class="side">(${item.count }개의 리뷰)</span>
								</div> 
							</div>
							<div class="buycart">
								<div class="heart">
									<c:forEach var="wishs" items="${wish }">
										<c:if test="${wishs.bookId == item.id }">
											<c:set var="wishId" value="${wishs.id }"></c:set>
											<c:set var="wishbookId" value="${wishs.bookId }"></c:set>
										</c:if>
									</c:forEach>
										<img class="wish" data-bookid="${item.id }" data-custid="${sessionScope.customer.id != null ? sessionScope.customer.id : 'false' }"
											alt="" 
											src="${wishbookId != '' && wishbookId == item.id ? '/resources/images/fullheart.png' : '/resources/images/heart.png'}"
											<c:if test="${wishbookId != '' && wishbookId == item.id }">
												data-wishid="${wishId }"
											</c:if>
											>
									<c:set var="wishId" value=""></c:set>
									<c:set var="wishbookId" value=""></c:set>
								</div>
								<div class="btn">
									<button class="cart" data-bookid="${item.id }">장바구니</button>
									<button class="buy" data-bookid="${item.id }">구매하기</button>
								</div>
							</div>
						</div>
					</div>
				</li>
			</c:forEach>
		</ul>
		<div class="page_box">
			<c:if test="${pager.page != 1 }">
				<div class="first"><a href="?page=1"><i class="fa fa-angle-double-right" aria-hidden="true"></i></a></div>
				<div class="prev"><a href="?page=${pager.prev }"><i class="fa fa-angle-right" aria-hidden="true"></i></a></div>
			</c:if>
			<c:forEach var="page" items="${pager.list }">
				<div class="page_num"><a href="?page=${page }" ${pager.page == page ? 'class="active"' : ''}>${page }</a></div>
			</c:forEach>
			<c:if test="${pager.last != pager.page }">
				<div class="next"><a href="?page=${pager.next }"><i class="fa fa-angle-right" aria-hidden="true"></i></a></div>
				<div class="final"><a href="?page=${pager.last }"><i class="fa fa-angle-double-right" aria-hidden="true"></i></a></div>
			</c:if>
		</div>
	</div>
	</section>
	
	<jsp:include page="../modal.jsp"/>

</body>
</html>