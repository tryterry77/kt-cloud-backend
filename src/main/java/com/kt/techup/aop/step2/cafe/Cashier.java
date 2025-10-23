package com.kt.techup.aop.step2.cafe;

import com.kt.techup.aop.step2.cafe.order.OrderSlip;
import com.kt.techup.aop.step2.product.Drink;
import com.kt.techup.aop.step2.product.Menu;
import com.kt.techup.aop.step2.product.Size;
import com.kt.techup.aop.step2.util.Util;

/**
 * [Step 2: 부가 기능(로깅, 재시도)의 침투]
 * Cashier 클래스는 Step 1과 동일하게 주문서 작성 및 서빙의 핵심 책임을 가집니다.
 * 하지만 이 단계에서는 '완료 로깅'과 '재시도 로직'과 같은 부가 기능이 핵심 로직 내부에 직접 추가되었습니다.
 *
 * 문제점:
 * - **코드의 복잡성 증가**: 핵심 비즈니스 로직(주문서 작성, 서빙)과 부가 기능이 섞여 코드를 이해하기 어렵게 만듭니다.
 * - **유지보수의 어려움**: 부가 기능의 변경이 필요할 경우, 여러 핵심 로직을 수정해야 합니다.
 * - **코드 중복**: 유사한 부가 기능이 여러 메서드에 반복적으로 나타날 수 있습니다.
 * - **응집도 저하**: 클래스가 자신의 핵심 책임 외에 다른 책임(로깅, 재시도)까지 떠안게 되어 응집도가 낮아집니다.
 * 이처럼 핵심 로직과 부가 기능이 강하게 결합되는 문제를 '횡단 관심사(Cross-cutting Concern)' 문제라고 합니다.
 */
public class Cashier {
	private final Cafe cafe;

	public Cashier(Cafe cafe) {
		this.cafe = cafe;
	}

	// 핵심 역할(기능)외에 부가적인 역할(기능)이 너무 많다. ( 재시도, 완료 로그 )
	public OrderSlip writeOrder(
		Customer customer, Menu menu,
		Size size) {
		int price = menu.price(size);
		OrderSlip slip = new OrderSlip(customer, menu,
			size, price);

		// [부가 기능: 재시도 로직]
		// 핵심 로직(주문서 작성)이 실패할 경우를 대비하여 재시도하는 로직이 직접 구현되어 있습니다.
		// 이 로직은 비즈니스 로직과는 직접적인 관련이 없지만, 코드 내부에 강하게 결합되어 있습니다.
		int retryCount = 0;
		while (true) {
			try {
				Util.randomException("Cashier::writeOrder"); // 50% 확률로 예외 발생 시뮬레이션
				break; // 성공 시 루프 탈출
			} catch (Exception e) {
				retryCount++;
				System.out.println("Try count: " + retryCount);
				if (retryCount >= Util.MAX_RETRY_COUNT) {
					throw e; // 최대 재시도 횟수 초과 시 예외 다시 던짐
				}
			}
		}

		System.out.println("[Cashier@" + cafe.name() + "] Wrote order slip: " + slip);
		System.out.println("[Cashier@" + cafe.name() + "] price = " + price + " KRW");

		// [부가 기능: 완료 로깅]
		// 핵심 로직 수행 완료 후 로그를 남기는 기능이 직접 호출됩니다.
		Util.LoggingCompleted("Cashier::writeOrder");

		return slip;
	}

	public void serve(Customer customer,
		Drink drink) {
		System.out.println("[Cashier@" + cafe.name() + "] Serving to customer: " + drink.label());

		// [부가 기능: 예외 발생 시뮬레이션]
		// 서빙 중 발생할 수 있는 예외를 시뮬레이션합니다. (재시도 로직은 여기서는 생략)
		Util.randomException("Cashier::serve");

		customer.receive(drink);

		// [부가 기능: 완료 로깅]
		// 핵심 로직 수행 완료 후 로그를 남기는 기능이 직접 호출됩니다.
		Util.LoggingCompleted("Cashier::serve");
	}
}
