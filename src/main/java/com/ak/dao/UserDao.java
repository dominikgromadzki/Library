package com.ak.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ak.model.User;

@Repository
public interface UserDao extends JpaRepository<User, Long> {

	//zwraca uzytkownika ktory ma adres email podany jako argument
	//tej metody
	User findByEmail(String email);
}
