package kr.ac.kopo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.ac.kopo.dao.BookDao;
import kr.ac.kopo.dao.ReviewDao;
import kr.ac.kopo.model.Book;
import kr.ac.kopo.page.Pager;

@Service
public class BookServiceImpl implements BookService{
	
	@Autowired
	BookDao dao;
	
	@Autowired
	ReviewDao reviewDao;

	@Override
	public List<Book> list(Pager pager) {
		// 도서의 총 개수를 pager total에 저장한다.
		pager.setTotal(dao.total(pager));
		
		List<Book> list = dao.list(pager);
		list.forEach(book -> {
			// 책에 대한 평균을 가져와서 Book객체에 있는 avg에 값을 넣어준다.
			book.setAvg(reviewDao.avg(book.getId()));	
			book.setCount(reviewDao.count(book.getId()));
		});
		
		return list;
	}

	@Override
	public void add(Book item) {
		dao.add(item);
	}

	@Override
	public Book item(Long id) {
		return dao.item(id);
	}

	@Override
	public List<Book> list(Set<Long> keySet) {
		// cart에 아무것도 없다면 비어있는 리스트를 전달
		if(keySet.isEmpty()) {
			return new ArrayList<Book>();
		}
		
		return dao.list(keySet);
	}

	@Override
	public List<Book> best() {
		return dao.best();
	}

	@Override
	public List<Book> novel(String wrtier) {
		return dao.novel(wrtier);
	}

	@Override
	public List<Book> koBookList() {
		return dao.koBookList();
	}

	@Override
	public List<Book> nkoBookList() {
		return dao.nkoBookList();
	}

	@Override
	public void update(Book item) {
		dao.update(item);
	}

	@Override
	public void delete(Long id) {
		dao.delete(id);
	}

}
