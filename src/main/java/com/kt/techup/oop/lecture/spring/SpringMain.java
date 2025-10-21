package com.kt.techup.oop.lecture.spring;

public class SpringMain {

	public static void main(String[] args) {
		// Spring Framework
		// Spring Boot
		// 두개는 명확히 다른 프로젝트 어떤점이 다른가?
		// Spring Boot는 Spring Framework를 더 쉽게 쓸 수 있게 도와주는 도구

		// Spring Framework 사용하는 이유
		// 1. 프레임워크이기 때문에, 일정한 수준의 개발 퀄리티 보장
		// 2. 개발 생산성 높아짐
		// 예전에는 개발자가 모든 것을 다 만들어야 함, 만드는 방식도 다양하고 프로젝트 내에서 계속 재사용 -> 프레임워크화 시켜서 공통적인 요소는 자동으로 돌아가게끔 만듬
		// 3. IoC/AOP/PSA 핵심 기술을 통해서 상당히 유연한 구조 제공
		// 4. POJO(Plan Old Java Object)를 완벽하게 지원 -> 순수한 자바객체

		//

		// new 를 계속 씀 -> heap 메모리에 계쏙 쌓인다 -> 메모리 누수 -> OOM
		// 성능도 안좋음

		// new 를 하지말자 -> 개발자가 하지말고, 프레임워크가 관리해주자 -> IoC
		// *Inversion of Control (제어의 역전)
		// Spring Container (Ioc Container)
		// 객체의 생명주기를 관리해줌 (생성 -> 사용 -> 소멸)
	}
}
