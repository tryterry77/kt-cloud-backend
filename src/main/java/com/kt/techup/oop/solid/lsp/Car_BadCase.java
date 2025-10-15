package com.kt.techup.oop.solid.lsp;

public class Car_BadCase {
	private int speed = 0;

	public void accelerate() {
		speed += 10;
		System.out.println("속도: " + speed);
	}

	public void brake() {
		speed = 0;
		System.out.println("정지");
	}
}
