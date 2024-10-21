package kr.ac.kopo.service;

import java.util.List;

import kr.ac.kopo.model.Book;

public interface BookService {

	List<Book> list();

	void add(Book item);

}
