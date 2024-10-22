package kr.ac.kopo.dao;

import java.util.List;

import kr.ac.kopo.model.Book;
import kr.ac.kopo.page.Pager;

public interface BookDao {

	List<Book> list(Pager pager);

	void add(Book item);

	void delete(String name);

	float total(Pager pager);

}
