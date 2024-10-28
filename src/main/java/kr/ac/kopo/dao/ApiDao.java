package kr.ac.kopo.dao;

import kr.ac.kopo.model.Api;

public interface ApiDao {

	void join(Api api);

	Api item(String email);
	
}
