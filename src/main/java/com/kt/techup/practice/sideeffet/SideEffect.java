package com.kt.techup.practice.sideeffet;

public class SideEffect {

	static public int applePrice = 1_000;

	static public class Customer {
		int appleCount;
	}

	public static void main(String[] args) {

		Customer customer = new Customer();
		customer.appleCount = 1;
	}
}
