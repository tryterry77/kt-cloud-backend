package com.kt.techup.oop.solid.lsp;

public class ElectricCar extends Car {
	int batteryLevel = 10;

	// 배터리 있을 때만 가속 가능
	@Override
	protected boolean canAccelerate() {
		return batteryLevel > 0; // 배터리 있을 때만 가속 가능
	}

	@Override
	public void accelerate() {
		batteryLevel -= 5;
		super.accelerate();
	}
}
