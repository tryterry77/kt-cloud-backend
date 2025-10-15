package com.kt.techup.oop.solid.srp;

// 책임 3: 이메일 발송 전담
public class EmailService {

	public void sendWelcomeEmail(User user) {
		System.out.println("이메일 발송: " + user.getEmail());
	}
}
