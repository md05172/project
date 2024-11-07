package kr.ac.kopo.service;

import kr.ac.kopo.model.Customer;
import kr.ac.kopo.model.Wish;

public interface WishService {

	int add(Wish wish);

	Customer wishList(Customer item);
	
	int delete(Long id);
	
}
