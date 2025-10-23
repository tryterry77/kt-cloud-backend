package com.kt.techup.aop.step3.cafe;

import com.kt.techup.aop.step3.cafe.order.OrderSlip;
import com.kt.techup.aop.step3.product.Drink;
import com.kt.techup.aop.step3.product.Menu;
import com.kt.techup.aop.step3.product.Size;
import com.kt.techup.aop.step3.util.Util;

public class Cashier {
	private final Cafe cafe; // 로깅/컨텍스트

	public Cashier(Cafe cafe) {
		this.cafe = cafe;
	}

	// 바리스타와 인터페이스가 달라서 하나로 데코레이터 패턴을 적용하기 어렵다.
	// OOP의 한계 
	public OrderSlip writeOrder(
		Customer customer, Menu menu,
		Size size) {
		int price = menu.price(size);
		OrderSlip slip = new OrderSlip(customer, menu,
			size, price);

		// 재시도 로직
		int retryCount = 0;
		while (true) {
			try {
				Util.randomException("Cashier::writeOrder");
				break;
			} catch (Exception e) {
				retryCount++;
				if (retryCount >= Util.MAX_RETRY_COUNT) {
					throw e;
				}
			}
		}

		System.out.println("[Cashier@" + cafe.name() + "] Wrote order slip: " + slip);
		System.out.println("[Cashier@" + cafe.name() + "] price = " + price + " KRW");

		// [완료 로깅]
		// System.out.println("[Log] Cashier::writeOrder completed.");
		Util.LoggingCompleted("Cashier::writeOrder");

		return slip;
	}

	public void serve(Customer customer,
		Drink drink) {
		System.out.println("[Cashier@" + cafe.name() + "] Serving to customer: " + drink.label());

		Util.randomException("Cashier::serve");

		customer.receive(drink);

		// [완료 로깅]
		// System.out.println("[Log] Cashier::serve completed.");
		Util.LoggingCompleted("Cashier::serve");
	}
}
