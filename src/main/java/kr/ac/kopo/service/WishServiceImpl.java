package kr.ac.kopo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.ac.kopo.dao.WishDao;
import kr.ac.kopo.model.Customer;
import kr.ac.kopo.model.Wish;

@Service
public class WishServiceImpl implements WishService{

	@Autowired
	WishDao dao;
	
	@Override
	public int add(Wish wish) {
		return dao.add(wish);
	}

	@Override
	public Customer wishList(Customer item) {
		return dao.wishList(item);
	}
	
	@Override
	public int delete(Long id) {
		return dao.delete(id);
	}

	@Override
	public Integer wishCount(Long custId) {
		return dao.wishCount(custId) != null ? dao.wishCount(custId) : 0;
	}

}
