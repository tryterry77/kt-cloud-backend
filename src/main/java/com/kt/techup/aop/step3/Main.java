package com.kt.techup.aop.step3;

import com.kt.techup.aop.step3.cafe.Cafe;
import com.kt.techup.aop.step3.cafe.Customer;
import com.kt.techup.aop.step3.cafe.baristar.Barista;
import com.kt.techup.aop.step3.cafe.baristar.BaristaLoggingDecorator;
import com.kt.techup.aop.step3.cafe.baristar.BaristaRetryDecorator;
import com.kt.techup.aop.step3.cafe.baristar.HumanBarista;
import com.kt.techup.aop.step3.cafe.baristar.RobotBarista;
import com.kt.techup.aop.step3.product.Menu;
import com.kt.techup.aop.step3.product.Size;

/**
 * [Step 3: 프록시/데코레이터 패턴 적용 및 OOP의 한계 경험]
 *
 * 이 단계에서는 Step 2에서 발생한 횡단 관심사(로깅, 재시도) 문제를
 * 프록시/데코레이터 패턴을 사용하여 해결하는 방법을 보여줍니다.
 * 핵심 비즈니스 로직(Barista의 음료 제조)과 부가 기능(로깅, 재시도)을 분리하여
 * 코드의 응집도를 높이고 중복을 줄이는 것을 목표로 합니다.
 *
 * 주요 개념:
 * - **데코레이터 패턴 (Decorator Pattern)**: 객체에 추가적인 기능을 동적으로 부여하기 위해 사용됩니다.
 *   기존 객체를 변경하지 않고 기능을 확장할 수 있습니다. 여기서는 `Barista` 인터페이스를 구현하는
 *   `BaristaLoggingDecorator`와 `BaristaRetryDecorator`가 데코레이터 역할을 합니다.
 * - **프록시 패턴 (Proxy Pattern)**: 특정 객체에 대한 접근을 제어하거나 기능을 추가할 때 사용됩니다.
 *   데코레이터 패턴과 유사하게 보일 수 있으나, 목적에 약간의 차이가 있습니다.
 *
 *   코드의 형식은 비슷하나 목적에 따라서 프록시/데코레이터 패턴으로 *표현*합니다.
 *   기능을 대신 실행하고 기존 기능을 꾸며준다는 느낌을 기억해야한다.
 *
 * 장점:
 * - 핵심 로직과 부가 기능의 분리: `HumanBarista`나 `RobotBarista`는 순수하게 음료 제조에만 집중합니다.
 * - 유연한 기능 조합: 로깅, 재시도 등의 부가 기능을 원하는 대로 조합하여 적용할 수 있습니다.
 * - 개방-폐쇄 원칙(OCP) 준수: 기존 코드를 변경하지 않고 새로운 기능을 추가할 수 있습니다.
 *
 * 한계점 (OOP의 한계):
 * - **많은 수의 클래스 생성**: 부가 기능 하나당 데코레이터 클래스가 필요하며, 기능이 많아질수록 클래스 수가 급증합니다.
 * - **복잡한 객체 생성 코드**: 여러 데코레이터를 적용할 경우, 객체를 생성하는 코드가 복잡해지고 길어집니다.
 *   (예: `new BaristaLoggingDecorator(new BaristaRetryDecorator(new HumanBarista()))`)
 * - **적용 대상의 제한**: 인터페이스를 구현하는 특정 메서드에만 적용 가능하며, 모든 메서드에 일괄적으로 적용하기 어렵습니다.
 *  캐셔 쪽에도 동일하게 적용하기 어렵다.
 *
 * 이 단계의 목적은 프록시/데코레이터 패턴을 통해 횡단 관심사 문제를 어느 정도 해결할 수 있지만,
 * 여전히 존재하는 한계점들을 인식하고, 이를 극복하기 위한 다음 단계(AOP)의 필요성을 느끼는 것입니다.
 */
public class Main {
	public static void main(String[] args) {
		try {
			Customer alice = new Customer("Alice");

			// --- Human Barista Cafe ---
			System.out.println("--- Human Barista Cafe ---");

			// 1. 핵심 기능을 가진 원본 객체를 생성한다.
			Barista humanBarista = new HumanBarista();

			// 2. 재시도 데코레이터로 원본을 감싸 재시도 기능을 추가한다.
			Barista retryableHumanBarista = new BaristaRetryDecorator(humanBarista);

			// 3. 로깅 데코레이터로 다시 감싸 로깅 기능을 추가한다.
			Barista finalHumanBarista = new BaristaLoggingDecorator(retryableHumanBarista);

			// 4. 모든 부가 기능이 추가된 최종 객체를 Cafe에 주입한다.
			Cafe humanCafe = new Cafe("TechUp Cafe (Human)", finalHumanBarista);
			humanCafe.acceptOrder(alice, Menu.AMERICANO, Size.SMALL);

			System.out.println();

			// --- Robot Barista Cafe ---
			System.out.println("--- Robot Barista Cafe ---");

			// 데코레이터 체인을 한 줄로 표현할 수도 있다.
			Cafe robotCafe = new Cafe("TechUp Cafe (Robot)",
				new BaristaLoggingDecorator( // 2. 로깅 기능 추가
					new BaristaRetryDecorator(   // 1. 재시도 기능 추가
						new RobotBarista()       // 0. 원본 객체
					)
				)
			);
			robotCafe.acceptOrder(alice, Menu.LATTE, Size.LARGE);

		} catch (Exception e) {
			// RetryDecorator에서 모든 재시도 실패 시 던진 예외를 여기서 처리
			System.out.println("Exception!!");
		}
	}
}
