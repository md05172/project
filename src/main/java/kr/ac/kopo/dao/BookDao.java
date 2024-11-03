package kr.ac.kopo.dao;

import java.util.List;
import java.util.Set;

import kr.ac.kopo.model.Book;
import kr.ac.kopo.page.Pager;

public interface BookDao {

	List<Book> list(Pager pager);

	void add(Book item);

	void delete(String name);

	int total(Pager pager);

	Book item(Long id);

	List<Book> list(Set<Long> keySet);

}
