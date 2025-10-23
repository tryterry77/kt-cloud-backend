package com.kt.techup.aop.step1.product;

// 제조 결과물
public class Drink {
	private final String label; // ex) "AMERICANO (SMALL)"

	public Drink(String label) {
		this.label = label;
	}

	public String label() {
		return label;
	}
}
