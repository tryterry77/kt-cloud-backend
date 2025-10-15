package com.kt.techup.oop.solid.ocp;

public class NaverPayPayment implements PaymentMethod {
	@Override
	public void processPayment(double amount) {
		System.out.println("네이버 페이로 결제, 결제 금액: " + amount);
		// 네이버 페이 결제 로직
	}
}
