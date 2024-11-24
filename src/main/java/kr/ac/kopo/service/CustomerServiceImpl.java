package kr.ac.kopo.service;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.ac.kopo.dao.CustomerDao;
import kr.ac.kopo.dao.ReviewDao;
import kr.ac.kopo.dao.WishDao;
import kr.ac.kopo.model.Customer;
import kr.ac.kopo.model.Mypage;

@Service
public class CustomerServiceImpl implements CustomerService{

	@Autowired
	CustomerDao dao;
	
	@Autowired
	WishDao wishdao;
	
	@Autowired
	ReviewDao reviewdao;
	
	@Transactional
	@Override
	public void join(Customer item) {
		item.setRole(1);
		
		dao.join(item);
	}

	@Override
	public Customer check(String email) {
		return dao.check(email);
	}

	@Override
	public boolean login(Customer item) {
		Customer customer = dao.check(item.getEmail());
		
		// 회원 찜 목록
		Customer wishList = wishdao.wishList(customer);
		
		// 회원 리뷰 목록
		Customer customerReview = reviewdao.customerReview(customer);
		
		// 회원이있고 비밀번호가 같다면
		if(customer != null) {
			if(item.getPassword().equals(customer.getPassword())) {
				// 찾은 customer 정보를 item에 복사해준다
				BeanUtils.copyProperties(customer, item);
				
				// 값이 담긴 item에 찜한 목록을 추가한다
				item.setWish(wishList.getWish());
		
				// 값이 담긴 item에 리뷰 목록을 추가한다
				item.setReview(customerReview.getReview());
					
				// 비밀번호는 null로
				item.setPassword(null);
				System.out.println("비밀번호 null 확인 " + item);
				return true;
			}
		}
		return false;
	}

	@Override
	public int phone(Customer customer) {
		return dao.phone(customer);
	}

	@Override
	public List<Mypage> list(Long id) {
		return dao.list(id);
	}

}
