package kr.ac.kopo.dao;

import kr.ac.kopo.model.Customer;
import kr.ac.kopo.model.Wish;

public interface WishDao {

	public int add(Wish wish);
	
	public Customer wishList(Customer item);
	
	public int delete(Long id);

	public Integer wishCount(Long custId);

}
