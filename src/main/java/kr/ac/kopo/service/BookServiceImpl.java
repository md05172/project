package kr.ac.kopo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.ac.kopo.dao.BookDao;
import kr.ac.kopo.model.Book;

@Service
public class BookServiceImpl implements BookService{
	
	@Autowired
	BookDao dao;

	@Override
	public List<Book> list() {
		return dao.list();
	}

	@Override
	public void add(Book item) {
		dao.add(item);
	}

}
