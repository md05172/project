package kr.ac.kopo.service;

import java.util.List;

import kr.ac.kopo.model.Customer;
import kr.ac.kopo.model.Mypage;

public interface CustomerService {

	void join(Customer item);

	Customer check(String email);

	boolean login(Customer item);

	int phone(Customer customer);

	List<Mypage> list(Long id);

}
