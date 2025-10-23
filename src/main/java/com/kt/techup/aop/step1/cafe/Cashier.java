package com.kt.techup.aop.step1.cafe;

import com.kt.techup.aop.step1.cafe.order.OrderSlip;
import com.kt.techup.aop.step1.product.Drink;
import com.kt.techup.aop.step1.product.Menu;
import com.kt.techup.aop.step1.product.Size;

public class Cashier {
	private final Cafe cafe;

	public Cashier(Cafe cafe) {
		this.cafe = cafe;
	}

	public OrderSlip writeOrder(
		Customer customer, Menu menu,
		Size size) {
		int price = menu.price(size);
		OrderSlip slip = new OrderSlip(customer, menu,
			size, price);
		System.out.println("[Cashier@" + cafe.name() + "] Wrote order slip: " + slip);
		System.out.println("[Cashier@" + cafe.name() + "] price = " + price + " KRW");
		return slip;
	}

	public void serve(Customer customer,
		Drink drink) {
		System.out.println("[Cashier@" + cafe.name() + "] Serving to customer: " + drink.label());
		customer.receive(drink);
	}
}
