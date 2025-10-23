package com.kt.techup.aop.step4.cafe;

import com.kt.techup.aop.step4.cafe.baristar.Barista;
import com.kt.techup.aop.step4.cafe.baristar.HumanBarista;
import com.kt.techup.aop.step4.cafe.order.OrderSlip;
import com.kt.techup.aop.step4.product.Drink;
import com.kt.techup.aop.step4.product.Menu;
import com.kt.techup.aop.step4.product.Size;

public class Cafe {
	private final String name;
	private final Barista barista; // 제조 구현 (다형성)
	private final Cashier cashier; // 정산/서빙

	public Cafe(String name, Barista barista) {
		this.name = name;
		this.barista = barista;
		this.cashier = new Cashier(this); // Cashier는 Barista를 전혀 모름
	}

	public Cafe(String name) {
		this(name, new HumanBarista());
	}

	public String name() {
		return name;
	}

	public void acceptOrder(
		Customer customer, Menu menu,
		Size size) {
		System.out.println(
			"[Cafe] " + name + " accepted order: " + menu + " (" + size + ") from " + customer.name());
		OrderSlip slip = cashier.writeOrder(customer, menu, size); // 작성은 캐셔 책임
		Drink drink = barista.prepare(slip);                       // 제조는 바리스타 책임
		cashier.serve(customer, drink);                            // 서빙은 캐셔 책임
	}
}
