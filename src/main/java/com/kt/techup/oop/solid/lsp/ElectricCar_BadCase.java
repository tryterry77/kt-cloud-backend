package com.kt.techup.oop.solid.lsp;

public class ElectricCar_BadCase extends Car_BadCase {
	private int batteryLevel = 10;

	@Override
	public void accelerate() {
		if (batteryLevel <= 0) {
			throw new RuntimeException("배터리가 없음. 액셀 불가능");
			// 부모의 accelerate()는 항상 작동하지만
			// 자식의 accelerate()는 예외를 던질 수 있음
			// LSP 위반
		}
		batteryLevel -= 5;
		super.accelerate();
	}
}
