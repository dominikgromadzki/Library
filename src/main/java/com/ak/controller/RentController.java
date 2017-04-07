package com.ak.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ak.model.Book;
import com.ak.model.Rent;
import com.ak.model.User;
import com.ak.service.BookService;
import com.ak.service.RentService;
import com.ak.service.UserService;

@Controller
public class RentController {
	@Autowired
	private RentService rentService;
	
	@Autowired
	private BookService bookService;
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value = "/rents", method = RequestMethod.GET)
	public String getRentsPage(Model model, Principal principal) {
		String email  = principal.getName();
		
		User user = userService.findByEmail(email);
		List<Rent> rents;
		
		if(user.getRole() == User.Role.USER) {
			rents = rentService.findByUserOrderByCreatedDateDesc(user);
		} else {
			rents = rentService.findAll();
		}
		
		model.addAttribute("rentsList", rents);
		
		return "rents";
	}
	
	//dodaje na podstawie idKsiazki (przekazanego przez url) nowe wypozyczenie dla
	//zalogowanego usera
	@RequestMapping(value="/rent/book/{bookId}")
	public String createRent(@PathVariable Long bookId, Principal principal) {
		
		String email = principal.getName();
		Book book = bookService.findOne(bookId);
		User user = userService.findByEmail(email);
		
		rentService.createRent(user, book);
		
		return "redirect:/rents";
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
