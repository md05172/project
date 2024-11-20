package kr.ac.kopo.service;

import java.util.List;
import java.util.Set;

import kr.ac.kopo.model.Book;
import kr.ac.kopo.page.Pager;

public interface BookService {

	List<Book> list(Pager pager);

	void add(Book item);

	Book item(Long id);

	List<Book> list(Set<Long> keySet);

	List<Book> best();

	List<Book> novel(String writer);
	
	List<Book> koBookList();
	
	List<Book> nkoBookList();

	void update(Book item);

	void delete(Long id);

}
