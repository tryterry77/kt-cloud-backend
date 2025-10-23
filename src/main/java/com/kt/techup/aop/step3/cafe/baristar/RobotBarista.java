package com.kt.techup.aop.step3.cafe.baristar;

import com.kt.techup.aop.step3.cafe.order.OrderSlip;
import com.kt.techup.aop.step3.product.Drink;
import com.kt.techup.aop.step3.util.Util;

/**
 * [Step 3: 핵심 기능에 집중하는 RobotBarista]
 * RobotBarista 클래스는 Barista 인터페이스의 구현체로, 음료 제조라는 핵심 책임에만 집중합니다.
 * Step 2에서 직접 포함되었던 '재시도 로직'과 '완료 로깅'과 같은 부가 기능들은
 * BaristaRetryDecorator와 BaristaLoggingDecorator로 분리되었습니다.
 *
 * 이로써 RobotBarista는 단일 책임 원칙(SRP)을 더욱 잘 준수하게 되며,
 * 코드의 가독성과 유지보수성이 향상됩니다.
 */
public class RobotBarista implements Barista {
	// [1차 리펙토링 및 개선]
	// 재시도 로직, 완료 로깅 재거
	// 핵심 기능에만 집중 (단일 책임 원칙)
	@Override
	public Drink prepare(OrderSlip slip) {
		System.out.println("[RobotBarista] Auto-brewing: " + slip.menu() + " (" + slip.size() + ")");

		// 여전히 일정 확률로 예외를 발생시켜 재시도 상황을 시뮬레이션합니다.
		// 이 예외는 BaristaRetryDecorator에 의해 처리됩니다.
		Util.randomException("RobotBarista::prepare");

		System.out.println("[RobotBarista] Completed: " + slip.menu() + " (" + slip.size() + ")");
		return new Drink(slip.menu().name() + " (" + slip.size() + ")");
	}
}
