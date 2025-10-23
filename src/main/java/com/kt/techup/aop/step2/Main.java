package com.kt.techup.aop.step2;

import com.kt.techup.aop.step2.cafe.Cafe;
import com.kt.techup.aop.step2.cafe.Customer;
import com.kt.techup.aop.step2.cafe.baristar.HumanBarista;
import com.kt.techup.aop.step2.cafe.baristar.RobotBarista;
import com.kt.techup.aop.step2.product.Menu;
import com.kt.techup.aop.step2.product.Size;

/**
 * [Step 2: 횡단 관심사(Cross-cutting Concern) 문제 발생]
 *
 * 이 단계에서는 Step 1에서 분리된 역할에 '부가 기능' (로깅, 재시도)을 추가합니다.
 * 부가 기능은 여러 핵심 로직(주문서 작성, 음료 제조, 서빙)에 걸쳐 반복적으로 나타나는 '횡단 관심사'입니다.
 *
 * 문제점:
 * 1. **코드의 복잡성 증가**: 핵심 비즈니스 로직과 부가 기능이 한데 섞여 코드를 이해하기 어렵게 만듭니다.
 *    (예: Cashier의 writeOrder, serve 메서드, Barista의 prepare 메서드)
 * 2. **코드 중복**: 동일하거나 유사한 부가 기능이 여러 곳에 반복적으로 구현됩니다.
 *    (예: 모든 핵심 메서드에 로깅 및 재시도 로직이 포함됨)
 * 3. **유지보수의 어려움**: 부가 기능의 변경(예: 로깅 방식 변경, 재시도 횟수 변경)이 필요할 경우,
 *    관련된 모든 핵심 로직을 찾아 수정해야 하므로 유지보수 비용이 증가합니다.
 * 4. **응집도 저하**: 클래스가 자신의 핵심 책임 외에 부가 기능까지 떠안게 되어 클래스의 응집도가 낮아집니다.
 *
 * 이 단계의 목표는 이러한 횡단 관심사 문제점을 명확히 인식하고,
 * 다음 단계에서 이 문제들을 어떻게 해결할 수 있을지 고민하는 것입니다.
 */
public class Main {
	public static void main(String[] args) {

		try {
			Customer alice = new Customer("Alice");

			System.out.println("--- 사람이 운영하는 카페 (부가 기능 포함) ---");
			Cafe humanCafe = new Cafe("TechUp Cafe (Human)",
				new HumanBarista());
			humanCafe.acceptOrder(alice, Menu.AMERICANO,
				Size.SMALL);

			System.out.println();

			System.out.println("--- 로봇이 운영하는 카페 (부가 기능 포함) ---");
			Cafe robotCafe = new Cafe("TechUp Cafe (Robot)",
				new RobotBarista());
			robotCafe.acceptOrder(alice, Menu.LATTE,
				Size.LARGE);
		} catch (Exception e) {
			// 부가 기능(재시도)이 실패하여 최종적으로 예외가 발생했을 때 처리하는 부분입니다.
			System.out.println("주문 처리 중 예외 발생: " + e.getMessage());
		}
	}
}
