package com.ak.service;


import java.util.List;

import com.ak.model.Book;
import com.ak.model.Rent;
import com.ak.model.User;

public interface RentService {
	void createRent(User user, Book book);
	List<Rent> findByUserOrderByCreatedDateDesc(User user);
	List<Rent> findAll();
}
