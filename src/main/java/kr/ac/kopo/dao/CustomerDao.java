package kr.ac.kopo.dao;

import java.util.List;

import kr.ac.kopo.model.Customer;
import kr.ac.kopo.model.Mypage;

public interface CustomerDao {

	public void join(Customer item);

	public Customer check(String email);

	public int phone(Customer customer);

	public List<Mypage> list(Long id);

	public void update(Customer customer);

}
