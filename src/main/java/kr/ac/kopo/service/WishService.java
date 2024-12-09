package kr.ac.kopo.service;

import kr.ac.kopo.model.Customer;
import kr.ac.kopo.model.Wish;

public interface WishService {

	// 찜 등록
	public int add(Wish wish);

	// 회원 찜 목록
	public Customer wishList(Customer item);
	
	// 찜 삭제
	public int delete(Long id);
	
	// 회원 찜 개수
	
	public Integer wishCount(Long custId);
}
