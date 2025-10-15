package com.kt.techup.oop.solid.lsp;

public class GasolineCar extends Car {

	// 휘발유차는 항상 가속 가능
	@Override
	protected boolean canAccelerate() {
		return true;
	}
}
