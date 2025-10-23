package com.kt.techup.aop.step1.product;

import java.util.Map;

public enum Menu {
	AMERICANO(4500, 5000, Map.of("en", "Americano", "kr", "아메리카노")),
	LATTE(5500, 6000, Map.of("en", "Latte", "kr", "라떼"));

	private final int smallPrice;
	private final int largePrice;
	private final Map<String, String> name;

	Menu(int smallPrice, int largePrice, Map<String, String> name) {
		this.smallPrice = smallPrice;
		this.largePrice = largePrice;
		this.name = name;
	}

	public int price(Size size) {
		return switch (size) {
			case SMALL -> smallPrice;
			case LARGE -> largePrice;
		};
	}

	public String getMenuName(String lang) {
		return name.getOrDefault(lang, name.get("en"));
	}
}
