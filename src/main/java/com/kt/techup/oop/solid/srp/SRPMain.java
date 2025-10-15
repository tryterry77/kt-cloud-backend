package com.kt.techup.oop.solid.srp;

// SRP
// "하나의 클래스는 하나의 책임만 가져야 한다"
// 클래스를 변경하는 이유는 단 하나여야 함
// 책임 = 변경의 이유
public class SRPMain {
	public static void main(String[] args) {
		User user = new User("김개발", "kim@example.com");

		UserRepository repository = new UserRepository();
		repository.save(user);

		EmailService emailService = new EmailService();
		emailService.sendWelcomeEmail(user);

		// User는 사용자 데이터 관리
		// UserRepository는 데이터베이스 저장 관리
		// EmailService는 이메일 발송 관리
	}
}
