package kr.ac.kopo.dao;

import kr.ac.kopo.model.Customer;
import kr.ac.kopo.model.Wish;

public interface WishDao {

	int add(Wish wish);
	
	Customer wishList(Customer item);
	
	int delete(Long id);

	Integer wishCount(Long custId);

}
