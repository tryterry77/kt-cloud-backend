package com.kt.techup.oop.lecture.spring.ioc;

public class IoCMain {
	public static void main(String[] args) {
		// IoC
		// 제어의 역전은 객체의 사용에 대한 제어권을 개발자가 아닌 외부에서 관리
		// IoC를 통해 애플리케이션의 구성 요소들이 느슨하게 결합
		// IoC는 객체지향 설계 원칙 중 하나로, 객체간 의존성을 줄이고 유연한 구조로 만듬
		// SOLID 원칙중 DIP, OCP

		// spring bean: spring container 내에서 생성하고 관리하는 객체
		// java bean: MVC 패턴에서 Model을 다룰때 사용하는 개념

		// bean을 등록해서 IoC 컨테이너에게 제어를 맡기는 방법
		// 1. xml 설정 파일
		// * 2. 어노테이션 활용 -> @Bean
		// 3. ApplicationContext 주입받아서 거기에 직접 등록

		// 위의 3개를 아무것도 하지 않은 자바 객체 -> Spring Bean일까? -> POJO

		// MemberService를 사용하기 위해 MemberDB를 개발자가 직접 객체를 생성한다.
		// 생성한 객체를 생성자 매개변수로 전달하여 MemberService 객체를 직접 생성한다.
		// 객체의 사용에 대한 제어권을 개발자가 갖고 있으며, 객체간에 강하게 결합되어 있다.
		MemberDB memberDB = new MemberDB();
		MemberService memberService = new MemberService(memberDB);

		// IoCContainer가 있다고 가정
		IocContainer iocContainer = new IocContainer();
		// 객체의 생명주기는 이제 IoCContainer에서 관리하기 때문에 개발자는 단순히 객체를 주입 받아서 사용
		MemberService memberService1 = iocContainer.getMemberService();
		MemberDB memberDB1 = iocContainer.getMemberDB();
	}

	static public class MemberService {
		private final MemberDB memberDB;

		public MemberService(MemberDB memberDB) {
			this.memberDB = memberDB;
		}
	}

	static public class MemberDB {

	}

	// IoCContainer에서 객체를 생성하고 제거하는 생명주기를 관리
	// *일종의 예시 이며 실제로는 다르게 동작
	static public class IocContainer {
		private final MemberDB memberDB = new MemberDB();
		private final MemberService memberService = new MemberService(memberDB);

		public MemberDB getMemberDB() {
			return memberDB;
		}

		public MemberService getMemberService() {
			return memberService;
		}
	}
}
