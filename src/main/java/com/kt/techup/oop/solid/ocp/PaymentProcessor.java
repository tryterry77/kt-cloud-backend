package com.kt.techup.oop.solid.ocp;

// OOP 옳은 예시

// 결제 처리기
// PaymentProcessor 수정 없이 결제수단(PaymentMethod)만 확장 가능
public class PaymentProcessor {
	public void processPayment(PaymentMethod paymentMethod, double amount) {
		paymentMethod.processPayment(amount);
	}
}
