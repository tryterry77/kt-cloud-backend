package com.kt.techup.oop.solid.ocp;

// OOP 위배 예시
//
public class PaymentProcessor_BadCase {
	public void processPayment(String paymentType, double amount) {
		if (paymentType.equals("CREDIT_CARD")) {
			System.out.println("신용 카드로 결제, 결제 금액: " + amount);
			// 신용카드 결제 로직
		} else if (paymentType.equals("KAKAO_PAY")) {
			System.out.println("카카오 페이로 결제, 결제 금액: " + amount);
			// 카카오 페이 결제 로직
		} else if (paymentType.equals("NAVER_PAY")) {
			System.out.println("네이버 페이로 결제, 결제 금액: " + amount);
			// 네이버 페이 결제 로직
		}
		// 새로운 결제 수단 추가할 때마다 PaymentProcessor 클래스를 계속 수정
	}
}
