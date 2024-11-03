package kr.ac.kopo.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.ac.kopo.dao.CustomerDao;
import kr.ac.kopo.dao.WishDao;
import kr.ac.kopo.model.Customer;

@Service
public class CustomerServiceImpl implements CustomerService{

	@Autowired
	CustomerDao dao;
	
	@Autowired
	WishDao wishdao;
	
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
		Customer wishList = wishdao.wishList(item);
		
		System.out.println("찜 목록 " + wishList);
		
		if(customer != null) {
			if(item.getPassword().equals(customer.getPassword())) {
				// customer에 있는 값을 item에 복사한다.
				if(wishList == null)
					BeanUtils.copyProperties(customer, item);
				else 
					BeanUtils.copyProperties(wishList, item);
					
				// 비밀번호는 보안을 위해 없애준다.
				item.setPassword(null);
				return true;
			}
		}
		return false;
	}

}
