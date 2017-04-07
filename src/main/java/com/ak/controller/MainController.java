package com.ak.controller;

import org.slf4j.event.Level;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ak.config.SecurityConfig;
import com.ak.model.User;
import com.ak.service.EmailService;
import com.ak.service.UserService;
import org.apache.log4j.*;

@Controller
public class MainController {
	@Autowired
	private UserService userService;
	
	@Autowired
	private EmailService emailService;
	
	private final static Logger logger = Logger.getLogger(MainController.class.getName());
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String getMainPage() {
		return "main";
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String getLoginPage() {
		return "login";
	}
	
	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public String getRegisterPage() {
		return "register";
	}
	
	//gdy user zatwierdzi dane do rejestracji
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String register(@ModelAttribute User user) {
		try {
			BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(SecurityConfig.PASSWORD_STRENGHT);
			String passwordEncoded = encoder.encode(user.getPassword());
			user.setPassword(passwordEncoded);
			userService.save(user);
		} catch(Exception ex) {
			logger.error(ex.getMessage());
			System.out.println(ex.getMessage());
			
			return "redirect:/register";
		}
		
		emailService.sendEmail("spring.szkolenie.test@gmail.com", user.getEmail(), "UDANA REJESTRACJA", "Welcome " + user.getFirstName());
		
		return "redirect:/login";
	}
	
	
	
	
	
	
	
	
	
	
}
