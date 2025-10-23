package com.kt.techup.aop.step4.cafe.baristar;

import com.kt.techup.aop.step4.aop.CompletionLoggable;
import com.kt.techup.aop.step4.aop.Retryable;
import com.kt.techup.aop.step4.cafe.order.OrderSlip;
import com.kt.techup.aop.step4.product.Drink;
import com.kt.techup.aop.step4.util.Util;

/**
 * AOP가 적용된 후, 이 클래스는 '음료를 제조한다'는 순수한 핵심 관심사(Core Concern)에만 집중
 * 재시도, 로깅과 같은 부가 기능(횡단 관심사(Cross-Cutting Concerns))는 모두 AOP 프레임워크가 처리,
 * 단일 책임 원칙 강화
 */
public class HumanBarista implements Barista {
	// @Retryable, @CompletionLoggable 의 적용 순서는 구현 방식에 따라 다르게 적용 될 수 있음.
	// 사용 전에 확인 해볼 것.
	@Override
	@Retryable
	@CompletionLoggable
	public Drink prepare(OrderSlip slip) {
		System.out.println("[HumanBarista] Hand-crafting: " + slip.menu() + " (" + slip.size() + ")");

		// 일정 확률로 예외를 발생시켜 부가 기능(재시도)의 동작을 시뮬레이션합니다.
		Util.randomException("HumanBarista::prepare");

		System.out.println("[HumanBarista] Done: " + slip.menu() + " (" + slip.size() + ")");
		return new Drink(slip.menu().name() + " (" + slip.size() + ")");
	}
}
