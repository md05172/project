package kr.ac.kopo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.ac.kopo.dao.ApiDao;
import kr.ac.kopo.model.Api;

@Service
public class ApiServiceImpl implements ApiService{
	
	@Autowired
	ApiDao dao;

	@Override
	public void join(Api api) {
		dao.join(api);
	}

	@Override
	public Api item(String email) {
		return dao.item(email);
	}

}
