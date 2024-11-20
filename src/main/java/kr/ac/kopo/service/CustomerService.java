package kr.ac.kopo.service;

import kr.ac.kopo.model.Customer;

public interface CustomerService {

	void join(Customer item);

	Customer check(String email);

	boolean login(Customer item);

	int phone(Customer customer);

}
