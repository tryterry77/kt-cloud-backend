package com.kt.techup.aop.step1.cafe.baristar;

import com.kt.techup.aop.step1.cafe.order.OrderSlip;
import com.kt.techup.aop.step1.product.Drink;

public class RobotBarista implements Barista {
	@Override
	public Drink prepare(OrderSlip slip) {
		System.out.println("[RobotBarista] Auto-brewing: " + slip.menu() + " (" + slip.size() + ")");
		// ...
		System.out.println("[RobotBarista] Completed: " + slip.menu() + " (" + slip.size() + ")");
		return new Drink(slip.menu().name() + " (" + slip.size() + ")");
	}
}
