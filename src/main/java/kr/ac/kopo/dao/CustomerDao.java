package kr.ac.kopo.dao;

import kr.ac.kopo.model.Customer;

public interface CustomerDao {

	void join(Customer item);

	Customer check(String email);

	int phone(Customer customer);

}
