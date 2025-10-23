package com.kt.techup.aop.step3_1.cafe.baristar;

import com.kt.techup.aop.step3.cafe.baristar.Barista;
import com.kt.techup.aop.step3.cafe.order.OrderSlip;
import com.kt.techup.aop.step3.product.Drink;
import com.kt.techup.aop.step3.util.Util;

/**
 * [Step 3: 데코레이터 패턴 - 재시도 기능 추가]
 * BaristaRetryDecorator는 Barista 인터페이스를 구현하여, 기존 Barista 객체에
 * '재시도(Retry)'라는 부가 기능을 동적으로 추가하는 데코레이터 역할을 합니다.
 * Step 2에서 Barista 구현체 내부에 직접 포함되었던 재시도 로직을 분리하여,
 * 핵심 비즈니스 로직(음료 제조)과 부가 기능(재시도)의 결합도를 낮춥니다.
 *
 * 데코레이터 패턴의 특징:
 * - **동일한 인터페이스 구현**: 원본 객체(Barista)와 동일한 Barista 인터페이스를 구현합니다.
 * - **원본 객체 포함**: 내부적으로 원본 Barista 객체를 참조(composition)하여 감쌉니다.
 * - **기능 위임 및 확장**: 원본 객체의 메서드를 호출(위임)하면서, 그 전후에 추가적인 로직(재시도)을 수행합니다.
 * - 이런 역할을 하는 객체를 Wrapper 객체라고도 부른다. ( 프록시/데코레이터는 래퍼 중 하나이다. )
 */
public class BaristaRetryDecorator implements com.kt.techup.aop.step3.cafe.baristar.Barista {

	private final com.kt.techup.aop.step3.cafe.baristar.Barista decoratedBarista; // 감싸는 대상 (e.g., HumanBarista, RobotBarista 또는 다른 데코레이터)

	public BaristaRetryDecorator(Barista decoratedBarista) {
		this.decoratedBarista = decoratedBarista;
	}

	/**
	 * 음료 제조 기능을 수행하며, 실패 시 지정된 횟수만큼 재시도합니다.
	 */
	@Override
	public Drink prepare(OrderSlip slip) {
		// [부가 기능: 재시도 로직]
		// 이 로직은 핵심 기능인 음료 제조(decoratedBarista.prepare(slip))가 실패했을 때
		// 다시 시도하는 역할을 합니다. 핵심 로직과는 독립적으로 존재합니다.
		int retryCount = 0;
		while (true) {
			try {
				// 핵심 기능은 감싸고 있는 원본 객체(decoratedBarista)에 위임한다.
				// 이 부분이 바로 프록시/데코레이터 패턴의 핵심입니다.
				return decoratedBarista.prepare(slip);
			} catch (Exception e) {
				retryCount++;
				System.out.println("[RetryDecorator] Retrying... (" + retryCount + "/" + Util.MAX_RETRY_COUNT + ")");
				if (retryCount >= Util.MAX_RETRY_COUNT) {
					System.err.println(
						"[RetryDecorator] Failed after " + retryCount + " retries. Original error: " + e.getMessage());
					throw e; // 최대 재시도 횟수 초과 시 예외를 다시 던짐
				}
			}
		}
	}
}
