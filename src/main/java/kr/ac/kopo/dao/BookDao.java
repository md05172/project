package kr.ac.kopo.dao;

import java.util.List;
import java.util.Set;

import kr.ac.kopo.model.Book;
import kr.ac.kopo.page.Pager;

public interface BookDao {

	public List<Book> list(Pager pager);

	public void add(Book item);
	
	public void delete(Long id);
	
	public int total(Pager pager);
	
	public Book item(Long id);
	
	public List<Book> list(Set<Long> keySet);
	
	public List<Book> best();
	
	public List<Book> novel(String writer);
	
	public List<Book> koBookList();
	
	public List<Book> nkoBookList();
	
	public void update(Book item);

}
