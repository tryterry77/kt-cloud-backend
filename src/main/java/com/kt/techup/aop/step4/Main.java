package com.kt.techup.aop.step4;

import com.kt.techup.aop.step4.aop.AopProxyFactory;
import com.kt.techup.aop.step4.cafe.Cafe;
import com.kt.techup.aop.step4.cafe.Customer;
import com.kt.techup.aop.step4.cafe.baristar.Barista;
import com.kt.techup.aop.step4.cafe.baristar.HumanBarista;
import com.kt.techup.aop.step4.cafe.baristar.RobotBarista;
import com.kt.techup.aop.step4.product.Menu;
import com.kt.techup.aop.step4.product.Size;

/**
 * [Step4]
 * - AOP 적용
 * - step3의 Main 클래스와 비교했을 때, 복잡한 데코레이터 생성 코드가 완전히 사라지고
 * - 각 역할들은 주어진 역할만 수행한다
 * - 바리스타 외에 캐셔에도 재시도, 완료 로깅 같은 부가 기능(역할)을 간단하게 추가할 수 있다.
 *
 *  [핵심용어] - **AOP (Aspect-Oriented Programming):** 횡단 관심사를 모듈화하여 핵심 로직과 분리하는 프로그래밍 패러다임.
 *
 *  대단한 것이 아니다. 더 좋은 코드 더 좋은 해결방법을 찾는 과정에서 나온 자연스러운 방법론(패러다임)이다.
 *
 */

public class Main {

	public static void main(String[] args) {
		try {
			Customer alice = new Customer("Alice");

			// --- Human Barista Cafe --- //
			System.out.println("--- Human Barista Cafe ---");

			// 1. 핵심 로직을 담고 있는 원본 객체를 생성합니다.
			Barista humanBarista = new HumanBarista();

			// 2. AOP 프레임워크(AopProxyFactory)를 통해 부가 기능이 적용된 프록시 객체를 얻습니다.
			//    개발자는 더 이상 new BaristaLoggingDecorator(new BaristaRetryDecorator(...)) 와 같은
			//    복잡하고 번거로운 코드를 작성할 필요가 없습니다.
			//    프레임워크가 HumanBarista 클래스의 @Loggable, @Retryable 어노테이션을 보고 알아서 처리해줍니다.
			Barista proxyHumanBarista = AopProxyFactory.createProxy(humanBarista);

			// 3. 프록시 객체를 Cafe에 주입합니다.
			//    Cafe 클래스는 자신이 사용하는 Barista 객체가 원본인지, 아니면 부가 기능이 추가된 프록시인지
			//    전혀 알 필요가 없습니다. (OCP: 개방-폐쇄 원칙)
			Cafe humanCafe = new Cafe("AOP Cafe (Human)", proxyHumanBarista);
			humanCafe.acceptOrder(alice, Menu.AMERICANO, Size.SMALL);

			System.out.println();

			// --- Robot Barista Cafe (한 줄로 간결하게) --- //
			System.out.println("--- Robot Barista Cafe ---");

			// AOP를 사용하면 객체 생성 및 부가 기능 적용 과정을 매우 간결하게 표현할 수 있습니다.
			Cafe robotCafe = new Cafe("AOP Cafe (Robot)",
				AopProxyFactory.createProxy(new RobotBarista()) // 프록시 생성 과정을 한 줄로 처리
			);
			robotCafe.acceptOrder(alice, Menu.LATTE, Size.LARGE);

		} catch (Exception e) {
			// AOP 프록시가 던진 최종 예외(모든 재시도 실패 후)를 여기서 처리합니다.
			System.out.println("주문 처리 중 예외 발생: " + e.getMessage());
		}
	}
}
