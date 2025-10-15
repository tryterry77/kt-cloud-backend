package com.kt.techup.oop.solid.ocp;

// 신용 카드로 결제하기
public class CreditCardPayment implements PaymentMethod {
	@Override
	public void processPayment(double amount) {
		System.out.println("신용 카드로 결제, 결제 금액: " + amount);
		// 신용카드 결제 로직
	}
}
