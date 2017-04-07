package com.ak.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ak.dao.BookDao;
import com.ak.model.Book;

@Service
public class BookServiceImpl implements BookService {
	
	@Autowired
	private BookDao bookDao;

	@Override
	public List<Book> findAll() {
		return bookDao.findAll();
	}

	@Override
	public Book findOne(Long id) {
		return bookDao.findOne(id);
	}

	@Override
	public void save(Book book) {
		bookDao.save(book);
		
	}

	@Override
	public void delete(Long id) {
		bookDao.delete(id);
		
	}

}
