package kr.ac.kopo.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Set;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.ac.kopo.model.Book;
import kr.ac.kopo.page.Pager;

@Repository
public class BookDaoImpl implements BookDao {
	
	@Autowired
	SqlSession sql;

	@Override
	public List<Book> list(Pager pager) {
		return sql.selectList("book.list", pager);
	}

	@Override
	public void add(Book item) {
		sql.insert("book.add", item);
	}

	@Override
	public void delete(String name) {
		sql.delete("book.delete", name);
	}

	@Override
	public int total(Pager pager) {
		return sql.selectOne("book.total", pager);
	}

	@Override
	public Book item(Long id) {
		return sql.selectOne("book.item", id);
	}

	@Override
	public List<Book> list(Set<Long> keySet) {
		HashMap<String, Set<Long>> map = new HashMap<String, Set<Long>>(); 

		map.put("keySet", keySet);
		
		return sql.selectList("book.list_keyset", map);
	}

}
