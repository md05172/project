<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<jsp:include page="deco.jsp"></jsp:include>
<link rel="stylesheet" href="/resources/css/cart.css">
<script type="text/javascript" src="/resources/js/cart.js"></script>
<title></title>
</head>
<body>   
   
   <jsp:include page="header.jsp"></jsp:include>
   
   <jsp:include page="nav.jsp"></jsp:include>
   
   <main>
      <div class="inner">
         <div class="title">
            <h1>장바구니</h1>
         </div>
         <div class="content">
            <div class="show">
               <div class="innershow">
                  <div class="all_box">
                     <label>
                        <input class="all_check" type="checkbox">
                        전체선택
                     </label>
                     <button class="delete">삭제하기</button>
                  </div>
               
                  <div class="books">
                     <table>
                        <thead>
                           <tr>
                              <th></th>
                              <th>상품정보</th>
                              <th>수량</th>
                              <th>금액</th>
                           </tr>
                        </thead>
                        
                        <tbody>
                           <c:forEach var="item" items="${list }">
                              <tr>
                                 <td>
                                    <div class="check">
                                       <input type="checkbox" class="checkid" name="checkid" data-bookid="${item.id }">
                                    </div>
                                 </td>
                                 <td class="base">
                                    <div>
                                       <div class="img_box">
                                          <a class="link">
                                             <img alt="" src="/upload/book/${item.path }">   
                                          </a>
                                       </div>
                                       <div class="item_info">
                                          <p>소득공제</p>
                                          <p>${item.name }</p>
                                          <p>${item.price }원</p>
                                       </div>
                                    </div>
                                 </td>
                                 <td>
                                    <div class="wrap">
                                       <div class="amount">
                                          <button data-bookid="${item.id }" class="up">+</button>
                                          <input class="cnt" type="text" 
                                          value="${sessionScope.cart.cart[item.id] != null && sessionScope.cart.cart[item.id] > 1 ? sessionScope.cart.cart[item.id] : 1}" 
                                          readonly>
                                           <button data-bookid="${item.id }" class="down">-</button>
                                       </div>
                                    </div>
                                 </td>
                                 <td>
                                    <div>${item.price }원</div>
                                 </td>
                              </tr>      
                           </c:forEach>
                        </tbody>
                        
                     </table>
                  </div>
                  
                  <c:if test="${list.size() < 1 }">
                     <div class="empty">
                        <span>장바구니가 비어 있어요</span>
                     </div>
                  </c:if>
                  
               </div>
               <aside class="guide_box">
                  <div class="total">
                     <div class="top">
                        <h2>주문 합계</h2>
                        <div></div>
                     </div>
                     <div class="body">
                        <ul>
                           <li>상품수 <span class="item_cnt">0개</span></li>
                           <li>상품금액 <span class="item_sum">0원</span></li>
                           <li>할인금액 <span>0원</span></li>
                           <li>배송비 <span>0원</span></li>
                        </ul>
                     </div>
                     <div class="foot">
                        <ul>
                           <li>전체 주문금액 <span class="price">0원</span></li>
                        </ul>
                     </div>
                     <div class="order">
                        <button>주문하기</button>
                     </div>
                  </div>
               </aside>
            </div>
         </div>
      </div>
   </main>
</body>
</html>