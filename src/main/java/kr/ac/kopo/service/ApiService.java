package kr.ac.kopo.service;

import kr.ac.kopo.model.Api;

public interface ApiService {

	void join(Api api);
	
	Api item(String email);
	
}
