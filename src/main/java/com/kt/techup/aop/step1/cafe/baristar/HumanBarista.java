package com.kt.techup.aop.step1.cafe.baristar;

import com.kt.techup.aop.step1.cafe.order.OrderSlip;
import com.kt.techup.aop.step1.product.Drink;

public class HumanBarista implements Barista {
	@Override
	public Drink prepare(OrderSlip slip) {
		System.out.println("[HumanBarista] Hand-crafting: " + slip.menu() + " (" + slip.size() + ")");
		// ...
		System.out.println("[HumanBarista] Done: " + slip.menu() + " (" + slip.size() + ")");
		return new Drink(slip.menu().name() + " (" + slip.size() + ")");
	}
}
