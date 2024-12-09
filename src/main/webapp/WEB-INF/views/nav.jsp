<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>    
<!-- 메뉴 -->
<nav>
	<div class="menu_box">
		<ul class="left">
			<li><a href="${pageContext.request.contextPath }/book/list/ko">국내도서</a></li>
			<li><a href="${pageContext.request.contextPath }/book/list/nko">외국도서</a></li>
			<li><a href="">베스트</a></li>
			<li><a href="">추천</a></li>
			<c:if test="${sessionScope.customer.role == 99 && sessionScope.customer != null}">
				<li><a href="${pageContext.request.contextPath }/book/add">도서등록</a></li>
			</c:if>
		</ul>
		
		<ul class="right">
			<li><a href="">사은픔</a></li>
			<li><a href="">문화행사</a></li>
			<li><a href="">이벤트</a></li>
			<li><a href="">출석체크</a></li>
			<li><a href="">노벨문학상</a></li>
		</ul>
	</div>
</nav>   