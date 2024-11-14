<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>   
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="/resources/css/order.css">
<jsp:include page="deco.jsp"></jsp:include>
<script type="text/javascript" src="/resources/js/daumAddress.js"></script>
<title>Insert title here</title>
</head>
<body>
	<div class="big_container">
		<div class="main_container">
			<div class="order_title">
				<h2>결제하기</h2>
			</div>
			<div class="order_container">
				<div class="left_box">
					<div class="order_items">
						<div>
							<h3>주문 상품 정보</h3>
						</div>
						
						<div class="item_box">
							<c:forEach var="item" items="${list }">
								<div class="item_out_box">
									<div class="item_wrap_box">
										<div class="item_img_box"><img alt="" src="/upload/book/${item.path }"></div>
										<div class="item_info_box">
											<div class="item_name_box"><p>${item.name }</p></div>
											<div class="item_writer_box"><p>${item.writer }</p></div>
											<div class="item_amount_box"><p><span class="item_amount">${item.count }</span><span>개</span></p></div>
											<div class="item_price_box"><span>₩</span><span><fmt:formatNumber value="${item.price }" type="Number" /></span></div>
										</div>
									</div>
									<div class="total">
										<span>₩</span><span><fmt:formatNumber value="${item.price * item.count }" type="Number" /></span>
									</div>
								</div>
							</c:forEach>
						</div> <!-- item_box -->
						
					</div> <!-- order_items -->
					
					<div class="customer_info">
						<div class=info_title>
							<h3>주문자 정보</h3>
						</div>
						
						<div class="customer_info_box">
							<div class="cust_left_box">
								<span>성함</span><span>${sessionScope.customer.name }</span>
							</div>
							<div class="cust_left_box">
								<span>전화번호</span><span>${fn:substring(sessionScope.customer.phone, 0, 3) }-${fn:substring(sessionScope.customer.phone, 4, 8) }-****</span>
							</div>
						</div> <!-- customer_info_box -->
						
					</div> <!-- customer_info -->
					
					<div class="shipping_info">
						<div>
							<h3>배송 정보</h3>
						</div>
					
						<div class="address_box">
							<div class="form-group address-group address_box">
								<input type="number" name="address.postcode" id="sample4_postcode" placeholder="우편번호" readonly="readonly">
								<input type="button" class="find btn" value="우편번호 찾기">
							</div>
							<div class="form-group address-group ad">
								<input type="text" name="address.roadaddress" id="sample4_roadAddress" placeholder="도로명주소">
							</div>
							<div class="form-group address-group ad">
								<input type="text" name="address.jibunaddress" id="sample4_jibunAddress" placeholder="지번주소">
							</div>
							<div class="form-group address-group ad">
								<input type="text" name="address.detailaddress" id="sample4_detailAddress" placeholder="상세주소">
								<input type="text" name="address.extraaddress" id="sample4_extraAddress" placeholder="참고항목">
							</div>
						</div>
							
					</div>
					
				</div> <!-- left_box -->
				
				<div class="right_box">
					<div class="simple_order_box">
						<div>
							<h3>주문 요약</h3>
						</div>
						<div>
							
						</div>
					</div>
					
				</div>
					
				
			</div> <!-- order_container -->
		</div> <!-- main_container -->
	</div> <!-- big_container -->
</body>
</html>