package com.kt.techup.oop.solid.ocp;

// OCP
// "확장에는 열려 있고, 수정에는 닫혀 있어야 한다"
// 새로운 기능 추가 시 기존 코드를 수정하지 않아야 함
// 추상화와 다형성을 활용
public class OCPMain {
	public static void main(String[] args) {
		PaymentProcessor paymentProcessor = new PaymentProcessor();

		// 다양한 결제 수단 활용
		paymentProcessor.processPayment(new CreditCardPayment(), 1000);
		paymentProcessor.processPayment(new KakaoPayPayment(), 1000);
		paymentProcessor.processPayment(new NaverPayPayment(), 1000);

		// 새 결제 수단 추가 시 PaymentMethod 구현만 하면 됨
		// 기존 PaymentProcessor는 수정할 필요 없음
	}
}
