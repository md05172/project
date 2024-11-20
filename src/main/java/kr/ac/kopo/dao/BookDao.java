package kr.ac.kopo.dao;

import java.util.List;
import java.util.Set;

import kr.ac.kopo.model.Book;
import kr.ac.kopo.page.Pager;

public interface BookDao {

	List<Book> list(Pager pager);

	void add(Book item);

	void delete(Long id);

	int total(Pager pager);

	Book item(Long id);

	List<Book> list(Set<Long> keySet);

	List<Book> best();

	List<Book> novel(String writer);

	List<Book> koBookList();
	
	List<Book> nkoBookList();

	void update(Book item);

}
