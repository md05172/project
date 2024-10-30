package kr.ac.kopo.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.ac.kopo.dao.CustomerDao;
import kr.ac.kopo.model.Customer;

@Service
public class CustomerServiceImpl implements CustomerService{

	@Autowired
	CustomerDao dao;
	
	@Override
	public void join(Customer item) {
		item.setRole(1);
		
		dao.join(item);
		
		Customer cust = check(item.getEmail());
		
		item.setId(cust.getId());
		
		dao.address(item);
	}

	@Override
	public Customer check(String email) {
		return dao.check(email);
	}

	@Override
	public boolean login(Customer item) {
		Customer customer = dao.check(item.getEmail());
		System.out.println("로그인 정보 확인 service " + customer);
		if(customer != null) {
			if(item.getPassword().equals(customer.getPassword())) {
				// customer에 있는 값을 item에 복사한다.
				BeanUtils.copyProperties(customer, item);
				// 비밀번호는 보안을 위해 없애준다.
				item.setPassword(null);
				return true;
			}
		}
		return false;
	}

}
