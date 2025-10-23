package com.kt.techup.aop.step4.cafe;

import com.kt.techup.aop.step4.aop.CompletionLoggable;
import com.kt.techup.aop.step4.aop.Retryable;
import com.kt.techup.aop.step4.cafe.order.OrderSlip;
import com.kt.techup.aop.step4.product.Drink;
import com.kt.techup.aop.step4.product.Menu;
import com.kt.techup.aop.step4.product.Size;
import com.kt.techup.aop.step4.util.Util;

public class Cashier {
	private final Cafe cafe; // 로깅/컨텍스트

	public Cashier(Cafe cafe) {
		this.cafe = cafe;
	}

	/**
	 * 바리스타 외에 캐셔에서도 동일한 부가기능(횡단 관심사)를 적용할 수 있다.
	 */
	@Retryable
	@CompletionLoggable
	public OrderSlip writeOrder(
		Customer customer, Menu menu,
		Size size) {
		int price = menu.price(size);
		OrderSlip slip = new OrderSlip(customer, menu,
			size, price);

		Util.randomException("Cashier::writeOrder");

		// 아래 로직 또한 불필요하여 제거 가능.
		// 재시도 로직
		// int retryCount = 0;
		// while (true) {
		// 	try {
		// 		Util.randomException("Cashier::writeOrder");
		// 		break;
		// 	} catch (Exception e) {
		// 		retryCount++;
		// 		if (retryCount >= Util.MAX_RETRY_COUNT) {
		// 			throw e;
		// 		}
		// 	}
		// }

		System.out.println("[Cashier@" + cafe.name() + "] Wrote order slip: " + slip);
		System.out.println("[Cashier@" + cafe.name() + "] price = " + price + " KRW");

		// 아래 로직 또한 AOP 적용을 통해 제거 가능.
		// [완료 로깅]
		// System.out.println("[Log] Cashier::writeOrder completed.");
		// Util.LoggingCompleted("Cashier::writeOrder");

		return slip;
	}

	@Retryable
	@CompletionLoggable
	public void serve(Customer customer,
		Drink drink) {
		System.out.println("[Cashier@" + cafe.name() + "] Serving to customer: " + drink.label());

		Util.randomException("Cashier::serve");

		customer.receive(drink);

		// [완료 로깅]
		// System.out.println("[Log] Cashier::serve completed.");
		// Util.LoggingCompleted("Cashier::serve");
	}
}
