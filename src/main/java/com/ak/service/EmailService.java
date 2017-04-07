package com.ak.service;

public interface EmailService {
	void sendEmail(String fromAddress, String toAddress, String subject, String body);
}
