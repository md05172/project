package kr.ac.kopo.service;

import java.util.List;
import java.util.Set;

import kr.ac.kopo.model.Book;
import kr.ac.kopo.page.Pager;

public interface BookService {

	List<Book> list(Pager pager);

	void add(Book item);

	void dummy();

	void init();

	Book item(Long id);

	List<Book> list(Set<Long> keySet);

}
