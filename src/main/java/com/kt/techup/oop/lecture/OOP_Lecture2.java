package com.kt.techup.oop.lecture;// 역할, 책임, 협력

// 역할 정리
// 카페 - 주문 흐름 제어
// 캐셔 - 주문받기, 음료 제공
// 바리스타 - 음료 제조
// 고객 - 주문(코드 구현은 불필요), 수령

// 리팩토링 몫표
// [1] -> 단일 책임 원칙을 지키자
// - OrderSlip 도입
// - 주문 절차를 Cafe로 집중 (각 객체간 역할 재분배)
// [2] -> 단일책임
// - Drink 도입
// [3] -> 의존관계 역전 원칙

public class OOP_Lecture2 {

	static public class OrderDto {
		private Customer customer;
		private Menu menu;
		private Size size;

		static public OrderDto from(Customer customer, Menu menu, Size size) {
			OrderDto dto = new OrderDto();
			dto.customer = customer;
			dto.menu = menu;
			dto.size = size;
			return dto;
		}
	}

	// [역할·책임] 사이즈를 나타내는 값의 역할. 상태만 가진 '값 객체' 성격.
	// [캡슐화] 외부에서는 Size가 SMALL/LARGE라는 '이름'만 알면 된다.
	enum Size {
		SMALL,
		LARGE
	}

	// [역할·책임] '메뉴'라는 도메인 개념을 대표하는 역할.
	// [책임] "자신의 가격을 스스로 안다" (size를 입력받아 가격을 계산/제공)
	// [캡슐화] 가격 로직(smallPrice/largePrice)은 내부에 숨기고, 외부엔 price(Size)
	enum Menu {
		AMERICANO(4500, 5000),
		LATTE(5500, 6000);
		private final int smallPrice;
		private final int largePrice;

		Menu(int smallPrice, int largePrice) {
			this.smallPrice = smallPrice;
			this.largePrice = largePrice;
		}

		// [요청/응답] 외부(sender)가 "가격 알려줘(price)"라는 '메시지(요청)'를 보내면,
		// Menu(수신자, receiver)는 '메서드(응답)'로 처리한다.
		// 외부가 내부 상태를 꺼내 계산하지 않고 '시킨다'. (TDA 원칙. Tell, Don't Ask)
		// [Cashier -> Menu] 가격 요청 (요청/메시지), 응답은 메서드의 반환값
		public int price(Size size) {
			return switch (size) {
				case SMALL -> smallPrice;
				case LARGE -> largePrice;
			};
		}
	}

	// [역할] 고객: 무엇을 마실지 결정, 결과(음료 이름)를 "수령".
	// [책임] 수령(receive)만 노출하고, 주문은 Cafe의 API를 사용하게 한다.
	static class Customer {
		private final String name;

		public Customer(String name) {
			this.name = name;
		}

		// [Cashier -> Customer] 결과 전달 (요청/메시지)
		public void receive(String drinkName) {
			System.out.println("[Customer] " + name + ": Received " + drinkName + ". Thanks!");
		}
	}

	// [역할] '캐시어' 역할: 가격 책정·주문 중재·알림 전달.
	// [책임]
	// - 가격을 계산(=메뉴에게 가격을 '요청'하여 받음)
	// - 바리스타에게 제조를 '요청' (메시지 전파)
	// - 완성된 음료를 고객에게 전달
	// [캡슐화] 가격 책정 흐름/제조 요청 순서는 캐시어 내부에서 관리.
	static class Cashier {
		private final Cafe cafe; // [소속 맥락] 출력/로깅 등에서 컨텍스트 표현
		private final Barista barista; // [협력자]

		public Cashier(Cafe cafe, Barista barista) {
			this.cafe = cafe;
			this.barista = barista;
		}

		// [Cafe -> Cashier] 주문 위임 (요청/메시지)
		// [협력 시나리오]
		// Cashier -> Menu: price(size) (요청/메시지)
		// Cashier -> Barista: prepare(menu, size) (요청/메시지)
		// Barista -> Cashier: 제조 결과 반환(응답)
		// Cashier -> Customer: receive(drink) (요청/메시지)
		public void takeOrder(Customer customer, Menu menu, Size size) {
			// 가격 계산 -> menu, size
			int price = priceOf(menu, size);
			System.out.println("[Cashier@" + cafe.name() + "] Price = " + price + " KRW");
			System.out.println("[Cashier@" + cafe.name() + "] Sending to barista: " + menu + " (" + size + ")");
			String drink = barista.prepare(menu, size);
			System.out.println("[Cashier@" + cafe.name() + "] Drink back from bar ista: " + drink);
			customer.receive(drink);
		}

		// 외부에서 가격 데이터 꺼내 계산하지 않음. ( [Tell, Don't Ask] '메뉴에게 시킨다'
		// [캡슐화] 가격 산정 변경의 영향 범위를 Cashier/Menu 내부로 제한
		private int priceOf(Menu menu, Size size) {
			return menu.price(size);
		}
	}

	// [역할] '바리스타' 역할: 제조를 담당하는 수행자.
	// [책임] 요청받은 메뉴/사이즈에 맞춰 음료를 만든다.
	// [협력] Cashier(sender)가 보내는 'prepare' 메시지를 수신(receiver)하여 응답.
	// [다형성 확장 포인트] 실전에서는 추후 'Barista'를 인터페이스(Preparer)로 바꾸어
	// HotBarista/ColdBarista, HumanBarista/RobotBarista 등 다양한 구현으로 확장 가능.
	static class Barista {
		// [Cashier -> Barista] 제조 요청 (요청/메시지), 응답은 drinkName 반환
		// [다형성] 나중에 Barista를 인터페이스(Preparer)로 치환해 구현 교체 가능
		public String prepare(Menu menu, Size size) {
			System.out.println("[Barista] Preparing: " + menu + " (" + size + ")");
			// ... (복잡한 제조 과정) — 이 내부 구현은 캡슐화되어 외부에 영향 없음.
			System.out.println("[Barista] Finished: " + menu + " (" + size + ")");
			return menu.name() + " (" + size + ")";
		}
	}

	// [역할] '카페'라는 협력의 장소(경계/맥락)를 제공하는 역할.
	// [책임] 내부 구성원(Cashier, Barista)을 '소유·조립'하고, 고객 주문을 접수하여 적절한 협력으로 연결.
	// [협력] Customer -> Cafe(acceptOrder) -> Cashier -> Barista -> Cashier ->Customer 메시지 흐름의 시작점.
	static class Cafe {
		private final String name;
		private final Barista barista; // [협력자]
		private final Cashier cashier; // [협력자]

		public Cafe(String name) {
			this.name = name;
			// [조립 책임] Cafe가 내부 객체를 생성/연결(Composition Root 관점)
			this.barista = new Barista();
			this.cashier = new Cashier(this, barista);
		}

		public String name() {
			return name;
		}

		// [요청/응답] Customer(sender)가 Cafe(receiver)에게 주문(acceptOrder) 메시지를 보냄.
		// Cafe는 이를 Cashier에게 위임하여 '협력'을 시작한다.
		// [Customer -> Cafe] 주문 접수 (요청/메시지). Cafe는 내부 협력을 오케스트레이션.
		public void acceptOrder(Customer customer, Menu menu, Size size) {
			System.out.println("[Cafe] " + name + " accepted order: " + menu + " (" + size + ")");
			cashier.takeOrder(customer, menu, size);
		}
	}

	// [시퀀스 요약]
	// Customer -> Cafe.acceptOrder(customer, menu, size)
	// Cafe -> Cashier.takeOrder -> (Cashier -> Menu.price) -> (Cashier -> Barista.prepare) -> Cashier -> Customer.receive
	public static void main(String[] args) {
		Cafe cafe = new Cafe("TechUp Cafe");
		Customer alice = new Customer("Alice");
		cafe.acceptOrder(alice, Menu.AMERICANO, Size.SMALL);
		System.out.println();
		cafe.acceptOrder(alice, Menu.LATTE, Size.LARGE);
	}
}