package com.ak.service;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.ak.model.User;

public interface UserService extends UserDetailsService {
	List<User> findAll();
	User findOne(Long id);
	void save(User user);
	void delete(Long id);
	User findByEmail(String email);
}
