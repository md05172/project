package kr.ac.kopo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.ac.kopo.dao.BookDao;
import kr.ac.kopo.model.Book;
import kr.ac.kopo.page.Pager;

@Service
public class BookServiceImpl implements BookService{
	
	@Autowired
	BookDao dao;

	@Override
	public List<Book> list(Pager pager) {
		// 도서의 총 개수를 pager total에 저장한다.
		pager.setTotal(dao.total(pager));
		
		return dao.list(pager);
	}

	@Override
	public void add(Book item) {
		dao.add(item);
	}

	@Transactional
	@Override
	public void dummy() {
		for (Long i = 1L; i < 100; i++) {
			Book item = new Book();
			item.setName("name" + i);
			item.setPublisher("출판사" + i);
			item.setPrice(1000 * i.intValue());
			item.setPath("path" + i);
			item.setInfo("info" + i);
			item.setWriter("저자" + i);
			item.setWriterInfo("저자소개" + i);
			item.setCategory("ko");
			dao.add(item);
		}
	}
	
	@Transactional
	@Override
	public void init() {
		for (Long i = 1L; i < 100; i++) {
			Book item = new Book();
			item.setName("name" + i);
			dao.delete(item.getName());
		}
	}

}
