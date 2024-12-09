package kr.ac.kopo.service;

import java.util.List;

import kr.ac.kopo.model.Customer;
import kr.ac.kopo.model.Mypage;

public interface CustomerService {

	public void join(Customer item);

	public Customer check(String email);

	public boolean login(Customer item);

	public int phone(Customer customer);

	public List<Mypage> list(Long id);

	public void update(Customer customer);

}
