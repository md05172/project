<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>    
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<jsp:include page="../deco.jsp"></jsp:include>
<link rel="stylesheet" href="/resources/css/book/detail.css">
<script type="text/javascript" src="/resources/js/detail.js"></script>
<title>Insert title here</title>
</head>
<body>
	<jsp:include page="../header.jsp"></jsp:include>
	<jsp:include page="../nav.jsp"></jsp:include>
	<div class="main_container">
		<div class="detail_container">
			<div class="left_box">
				<div class="inner_box">
					<div class="img_box">
						<img alt="" src="/upload/book/${item.path }">
					</div>
				</div>
			</div>	
			
			<div class="right_box">
				<p class="name">${item.name }</p>
				<span class="writer">${item.writer }</span>
				<span class="publisher">${item.publisher }</span>
				<div class="heart">
					<c:if test="${wish.bookId ==  item.id}">
						<c:set var="wishId" value="${wish.id }"/>
					</c:if>
					<img class="wish" data-bookid="${item.id }" data-custid="${sessionScope.customer.id != null ? sessionScope.customer.id : 'false' }"
						alt="" src="${fn:contains(sessionScope.customer.wish, item.id) ? '/resources/images/fullheart.png' : '/resources/images/heart.png'}"
						 <c:if test="${fn:contains(sessionScope.customer.wish, item.id)}">
					        data-wishid="${wishId}"
					    </c:if> >
				</div>
				<div class="price_box">
					<span class="price">${item.price }</span>
					<span class="won">원</span>
				</div>
				
				<div class="delivery_info">
				    <p>배송 기간: 2~3일 (주말 제외)</p>
				</div>
				
				<div class="social_share">
				    <button class="share_facebook">Facebook</button>
				    <button class="share_twitter">Twitter</button>
				    <button class="share_instagram">Instagram</button>
				</div>
				
				<div class="buycart_box">
					<div class="amount_box">
						<div class="amount">
							<div class="plus_minus">
								<button>-</button>
								<input type="text" value="1">
								<button>+</button>
							</div>
							<div class="cart_price_box">
								<span>${item.price }</span>
								<span>원</span>
							</div>
						</div>
						<div class="btn_box">
							<button class="cart">장바구니</button>
							<button class="buy">구매하기</button>
						</div>
					</div>
				</div>
			</div>
		</div>
		
		<div class="detail_nav_list">
			<div class="detail_nav">
				<div class="nav_box">
					<ul class="nav">
						<li><a href="#writer_info">작가 소개</a></li>
						<li><a href="#info">책 소개</a></li>
						<li><a href="#review">리뷰</a></li>
					</ul>
				</div>
			</div>
		</div>
		
		<div class="book_detail_box">
			<div class="faq_section">
		    	<h3>자주 묻는 질문</h3>
			    <ul class="question">
			        <li>Q: 이 책은 언제 재입고 되나요?</li>
			        <li>A: 현재 재입고 예정이 없습니다. 추후 공지 드리겠습니다.</li>
			        <li>Q: 배송 기간은 얼마나 걸리나요?</li>
			        <li>A: 보통 2-3일 내에 배송됩니다.</li>
			    </ul>
			</div>
			
			<div class="related_books">
			    <h3>이 책을 좋아하셨다면, 이런 책도 추천드립니다!</h3>
			    <ul>
			        <li>
			            <a href="/book/detail/123">
			                <img src="/upload/book/related_book_1.jpg" alt="관련도서">
			                <p>책 제목</p>
			            </a>
			        </li>
			        <li>
			            <a href="/book/detail/124">
			                <img src="/upload/book/related_book_2.jpg" alt="관련도서">
			                <p>책 제목</p>
			            </a>
			        </li>
			        <!-- 다른 추천 도서들 -->
			    </ul>
			</div>
			
			<div class="book_info">
				<div class="head">
					<h3>책소개</h3>
				</div>
				<div class="duse">
					${item.info }
				</div>
			</div>
			
			<div class="writer_info" id="writer_info">
				<div class="head">
					<h3>작가정보</h3>
				</div>
				<div class="winfo">
					${item.writerInfo }
				</div>
			</div>
			
			<div class="review_box">
				<div>
					<h3>100자평</h3>
				</div>
				<div class="inner_review_box">
					<div class="star_box">
						<div class="rating" id="rating"></div>
						<div class="Hright2" style="padding-top:10px;">
		                    <div class="caution">글 작성 유의사항 <div><img alt="" src="/resources/images/under.png"></div></div>
		                    <div id="help_CommentReviewInfo" class="popup hidden">
						
								<div class="Ere_layerst_Tit2">
									<h3 class="Ere_textC "> 게시물 운영 원칙</h3>
								    <div class="close"></div>
								    <div class="Ere_space20"></div>
								    <div class="bt_list3">
							    	이 곳은 책의 내용에 대한 감상이나 후기를 작성하는 곳입니다. 아래의 내용이 포함된 글의 경우 상품 소개 페이지에서 노출되지 않을 수 있으니 유의하여 작성해주세요.
							    	</div>
							    	<div class="Ere_space5"></div>
							    
							    	<div class="bt_list3">
								        <ul>
								            <li>1. 독서 후기와 무관한 주문 문의나 배송 관련 문의, 요청사항 (<a href="#"><strong>고객센터</strong></a>로 문의해주세요.)</li>
								            <li>2. 타인의 글에 대한 반박이나 논쟁의 성격을 담은 글</li>
								            <li>3. 편파적인 내용이나 잘못된 정보가 포함된 글</li>
								            <li>4. 인신공격, 명예훼손, 특정인/종교/계층을 비방하는 내용, 혐오/차별/비하/모욕적인 표현</li>
								            <li>5. 비속어를 사용하거나 자극적인 내용을 담은 글</li>
								            <li>6. 짧은 의태어나 의성어로만 작성된 글이나 반복적인 내용</li>
								            <li>7. 해당 상품의 내용과 무관한, 저자, 역자, 출판사, 공급사 등에 관한 글</li>
								            <li>8. (문학 등 분야의 도서에서) 결말이 암시된 내용의 글</li>
											<li>9. 저작권 분쟁의 소지가 있는 글</li>
								        </ul>
						    		</div>
								</div>
							</div>
                		</div>
					</div>
					<div class="review_write">
						<textarea placeholder="이 책을 읽고 어떤 느낌을 받으셨나요? 리뷰를 남기고 다른 독자들과 함께 공유해보세요."></textarea>
						<button class="review_submit" data-bookid="${item.id }" data-custid="${sessionScope.customer != null ? sessionScope.customer.id : 'false' }">작성</button>
					</div>
				</div>
			</div>
			
			<div class="claim_box">
			
				<h2>교환/반품/환불</h2>
			
				<table>
					<colgroup>
						<col width="200">
						<col>
					</colgroup>
					<tbody>
						<tr>
							<th>반품/교환방법</th>
							<td>
								<ul>
									<li>마이페이지 > 주문관리 > 주문/배송조회 > 주문조회 후  [1:1상담신청]  또는 고객센터 (8282-8282)</li>
									<li>※ 오픈마켓, 해외배송 주문상품 문의 시 [1:1상담신청] 또는 고객센터 (8282-8282)</li>
								</ul>
							</td>
						</tr>
						<tr>
							<th>반품/교환 가능기간</th>
							<td>
								<ul>
									<li>변심반품의 경우 수령 후 7일 이내</li>
									<li>상품의 결함 및 계약내용과 다를 경우 문제점 발견 후 30일 이내</li>
								</ul>
							</td>
						</tr>
						<tr>
							<th>반품/교환비용</th>
							<td>
								<ul>
									<li>단순변심 혹은 구매착오로 인한 반품/교환은 반송료 고객 부담</li>
									<li>해외직배송 도서 구매 후 단순변심에 의한 취소 및 반품 시 도서판매가의 20% 수수료 부과</li>
								</ul>
							</td>
						</tr>
						<tr>
							<th>반품/교환 불가 사유</th>
							<td>
								<ul>
									<li>소비자의 책임 있는 사유로 상품 등이 손실 또는 훼손된 경우</li>
									<li>소비자의 사용, 포장 개봉에 의해 상품 등의 가치가 현저히 감소한 경우 <br> 예) 만화, 잡지, 수험서 및 문제집류</li>
									<li>복제가 가능한 상품 등의 포장을 훼손한 경우 <br> 예) 음반/DVD/비디오, 소프트웨어, 만화책, 잡지, 영상 화보집</li>
									<li>소비자의 요청에 따라 개별적으로 주문 제작되는 상품의 경우</li>
									<li>디지털 컨텐츠인 eBook, 오디오북 등을 1회 이상 다운로드를 받았을 경우</li>
									<li>시간의 경과에 의해 재판매가 곤란한 정도로 가치가 현저히 감소한 경우</li>
									<li>전자상거래 등에서의 소비자보호에 관한 법률이 정하는 소비자 청약철회 제한 내용에 해당되는 경우</li>
								</ul>
							</td>
						</tr>
						<tr>
							<th>상품 품절</th>
							<td>
								<ul>
									<li>공급사(출판사) 재고 사정에 의해 품절/지연될 수 있으며, 품절 시 관련 사항에 대해서는 이메일과 문자로 안내드리겠습니다.</li>
									<li>※ 오픈마켓, 해외배송 주문상품 문의 시 [1:1상담신청] 또는 고객센터 (8282-8282)</li>
								</ul>
							</td>
						</tr>
						<tr>
							<th>소비자 피해보상
								환불지연에 따른 배상</th>
							<td>
								<ul>
									<li>상품의 불량에 의한 교환, A/S, 환불, 품질보증 및 피해보상 등에 관한 사항은 소비자분쟁 해결 기준 (공정거래위원회 고시)에 준하여 처리됨</li>
									<li>대금 환불 및 환불지연에 따른 배상금 지급 조건, 절차 등은 전자상거래 등에서의 소비자 보호에 관한 법률에 따라 처리함</li>
								</ul>
							</td>
						</tr>
					</tbody>
				</table>
			</div>
		</div>
	</div>
</body>
</html>