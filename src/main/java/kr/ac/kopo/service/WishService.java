package kr.ac.kopo.service;

import kr.ac.kopo.model.Wish;

public interface WishService {

	int add(Wish wish);

	int delete(Long id);
	
}
