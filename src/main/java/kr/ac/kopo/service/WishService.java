package kr.ac.kopo.service;

import kr.ac.kopo.model.Customer;
import kr.ac.kopo.model.Wish;

public interface WishService {

	// 찜 등록
	int add(Wish wish);

	// 회원 찜 목록
	Customer wishList(Customer item);
	
	// 찜 삭제
	int delete(Long id);
	
	// 회원 찜 개수
	
	Integer wishCount(Long custId);
}
