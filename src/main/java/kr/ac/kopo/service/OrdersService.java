package kr.ac.kopo.service;

import kr.ac.kopo.model.Address;
import kr.ac.kopo.model.Customer;
import kr.ac.kopo.model.Orders;

public interface OrdersService {
	
	// 주문테이블에서 고객과 주소를 비교해서 있는 주소인지 확인
	Address check(Address address);

	// 주문할때 주소 추가
	void address(Customer customer);

	// 주문하기
	void add(Orders orders);

	// 주문 하고 돌아가기 눌렀을때 다시 주문되는걸 막기위함.
	boolean orderCheck(String orderId);
	
	// 회원 주문 건수
	Integer ordersCount(Long custId);
	
	// 회원 주문 금액
	Integer ordersSum(Long custId);
	
	// 회원 주문 도서 수
	Integer ordersBookCount(Long custId);
}
