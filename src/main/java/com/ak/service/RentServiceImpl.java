package com.ak.service;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ak.dao.BookDao;
import com.ak.dao.RentDao;
import com.ak.model.Book;
import com.ak.model.Rent;
import com.ak.model.User;

@Service
public class RentServiceImpl implements RentService {
	
	@Autowired
	private RentDao rentDao;
	
	@Autowired
	private BookDao bookDao;

	@Transactional
	@Override
	public void createRent(User user, Book book) {
		Rent rent = new Rent(user, book);
		rentDao.save(rent);
		book.decrementAvailable();
		bookDao.save(book);
		
	}

	@Override
	public List<Rent> findByUserOrderByCreatedDateDesc(User user) {
		return rentDao.findByUserOrderByCreatedDateDesc(user);
	}

	@Override
	public List<Rent> findAll() {
		return rentDao.findAll();
	}

}
