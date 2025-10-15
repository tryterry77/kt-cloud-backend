package com.kt.techup.oop.solid.ocp;

public class KakaoPayPayment implements PaymentMethod {
	@Override
	public void processPayment(double amount) {
		System.out.println("카카오 페이로 결제, 결제 금액: " + amount);
		// 카카오 페이 결제 로직
	}
}
