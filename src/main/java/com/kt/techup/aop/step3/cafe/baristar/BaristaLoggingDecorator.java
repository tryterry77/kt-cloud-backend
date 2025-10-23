package com.kt.techup.aop.step3.cafe.baristar;

import com.kt.techup.aop.step3.cafe.order.OrderSlip;
import com.kt.techup.aop.step3.product.Drink;
import com.kt.techup.aop.step3.util.Util;

/**
 * [Step 3: 데코레이터 패턴 - 로깅 기능 추가]
 * BaristaLoggingDecorator는 Barista 인터페이스를 구현하여, 기존 Barista 객체에
 * '완료 로깅'이라는 부가 기능을 동적으로 추가하는 데코레이터 역할을 합니다.
 * Step 2에서 Barista 구현체 내부에 직접 포함되었던 로깅 로직을 분리하여,
 * 핵심 비즈니스 로직(음료 제조)과 부가 기능(로깅)의 결합도를 낮춥니다.
 *
 * 데코레이터 패턴의 특징:
 * - **동일한 인터페이스 구현**: 원본 객체(Barista)와 동일한 Barista 인터페이스를 구현합니다.
 * - **원본 객체 포함**: 내부적으로 원본 Barista 객체를 참조(composition)하여 감쌉니다.
 * - **기능 위임 및 확장**: 원본 객체의 메서드를 호출(위임)한 후, 추가적인 로직(로깅)을 수행합니다.
 * - 이런 역할을 하는 객체를 Wrapper 객체라고도 부른다. ( 프록시/데코레이터는 래퍼 중 하나이다. )
 */
public class BaristaLoggingDecorator implements Barista {

	private final Barista decoratedBarista; // 감싸는 대상 (e.g., HumanBarista, RobotBarista 또는 다른 데코레이터)

	public BaristaLoggingDecorator(Barista decoratedBarista) {
		this.decoratedBarista = decoratedBarista;
	}

	/**
	 * 음료 제조 기능을 수행하고, 완료 후 로깅을 남깁니다.
	 */
	@Override
	public Drink prepare(OrderSlip slip) {
		// 1. 핵심 기능은 감싸고 있는 원본 객체(decoratedBarista)에 위임한다.
		// 이 부분이 바로 프록시/데코레이터 패턴의 핵심입니다.
		Drink drink = decoratedBarista.prepare(slip);

		// 2. [부가 기능: 완료 로깅]을 추가로 수행한다.
		// 핵심 로직 수행 완료 후 로그를 남기는 기능이 여기서 수행됩니다.
		String decoratedClassName = decoratedBarista.getClass().getSimpleName();
		Util.LoggingCompleted(decoratedClassName + "::prepare");

		// 3. 핵심 기능의 결과를 그대로 반환한다.
		return drink;
	}
}
