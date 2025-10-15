package com.kt.techup.oop.solid.ocp;

// 결제 수단을 추상화 함
public interface PaymentMethod {

	void processPayment(double amount);
}
