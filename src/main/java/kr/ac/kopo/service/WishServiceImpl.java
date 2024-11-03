package kr.ac.kopo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.ac.kopo.dao.WishDao;
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
	public int delete(Long id) {
		return dao.delete(id);
	}
	
}
