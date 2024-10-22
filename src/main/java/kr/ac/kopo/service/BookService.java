package kr.ac.kopo.service;

import java.util.List;

import kr.ac.kopo.model.Book;
import kr.ac.kopo.page.Pager;

public interface BookService {

	List<Book> list(Pager pager);

	void add(Book item);

	void dummy();

	void init();

}
