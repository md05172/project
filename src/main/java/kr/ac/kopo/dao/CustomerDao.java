package kr.ac.kopo.dao;

import java.util.List;

import kr.ac.kopo.model.Customer;
import kr.ac.kopo.model.Mypage;

public interface CustomerDao {

	void join(Customer item);

	Customer check(String email);

	int phone(Customer customer);

	List<Mypage> list(Long id);

	void update(Customer customer);

}
