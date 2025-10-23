package com.kt.techup.aop.step1;

import com.kt.techup.aop.step1.cafe.Cafe;
import com.kt.techup.aop.step1.cafe.Customer;
import com.kt.techup.aop.step1.cafe.baristar.HumanBarista;
import com.kt.techup.aop.step1.cafe.baristar.RobotBarista;
import com.kt.techup.aop.step1.product.Menu;
import com.kt.techup.aop.step1.product.Size;

/**
 * 핵심 역할(기능)
 * - Cashier는 "주문지(OrderSlip)를 작성"만 한다.
 * - Barista는 "주문지로 제조"한다.
 * - Cashier는 "제조 결과를 고객에게 알린다(서빙)".
 *
 *  부가 역할(기능)
 *  - 캐셔, 바리스타 역할 수행 완료 로깅
 *  - 의사소통이 잘 되었는 지 확인 후, 재시도 진행 ( 검증 / 트랜잭션 )
 *
 *  TODO
 *  - 역할(기능) 별 코드 파일 분리
 *  - 부가 기능 추가 ( 각 역할 별 재시도, 완료 로깅 )
 *
 *  - 일반적인 함수로 기능 구현 -> 문제점 발견
 *  - 리펙토링 및 개선 ( 프록시/데코레이터 패턴 ) -> 문제점 발견
 *  - 리펙토링 및 개선 ( AOP 적용 )
 *  - AOP 개념 리뷰
 *
 *
 *  [Step 1]
 *  - 파일 분리
 *
 *  [Step 2]
 *  - 완료 로깅 -> 로깅 함수 구현
 *  - 재시도 로직 -> ??
 *
 *  [Step3]
 *  - 데코레이터, 프록시 패턴 적용
 *  - OOP 한계
 *
 *  [Step4]
 *  - AOP 적용
 */

/**
 *  [Step 1]
 *  - 파일 분리
 */
public class Main {
	public static void main(String[] args) {
		Customer alice = new Customer("Alice");

		System.out.println("--- 사람이 운영하는 카페 ---");
		Cafe humanCafe = new Cafe("TechUp Cafe (Human)",
			new HumanBarista());
		humanCafe.acceptOrder(alice, Menu.AMERICANO,
			Size.SMALL);

		System.out.println();

		System.out.println("--- 로봇이 운영하는 카페 ---");
		Cafe robotCafe = new Cafe("TechUp Cafe (Robot)",
			new RobotBarista());
		robotCafe.acceptOrder(alice, Menu.LATTE,
			Size.LARGE);
	}
}
