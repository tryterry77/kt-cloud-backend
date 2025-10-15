package com.kt.techup.oop.solid.lsp;

public abstract class Car {
	protected int speed = 0;

	public void accelerate() {
		if (canAccelerate()) {
			speed += 10;
			System.out.println("속도: " + speed);
		} else {
			System.out.println("현재 액셀 불가능");
		}
	}

	// 추상 메서드: 자식이 반드시 구현해야 함
	protected abstract boolean canAccelerate();

	public void brake() {
		speed = 0;
		System.out.println("정지");
	}
}
