package com.kt.techup.aop.step4.cafe;

import com.kt.techup.aop.step4.product.Drink;

public class Customer {
	private final String name;

	public Customer(String name) {
		this.name = name;
	}

	public String name() {
		return name;
	}

	public void receive(Drink drink) {
		System.out.println("[Customer] " + name + ": Received " + drink.label() + ". Thanks!");
	}
}
