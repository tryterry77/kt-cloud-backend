package com.kt.techup.oop.lecture;// 역할, 책임, 협력

// 역할 정리

// 캐셔 - 주문지(OrderSlip)을 작성만 한다
// 바리스타 - 주문지(OrderSlip)로 제조한다.
// 캐셔 - 제조 결과를 고객에게 알린다
// 바리스타 다형성 적용

public class OOP_Lecture2 {

	// 주문지에는 어떤 정보가 필요할까?
	// 고객, 메뉴, 사이즈, 가격...
	static public class OrderSlip {
		private Customer customer;
		private Menu menu;
		private Size size;
		private int price;

		public OrderSlip(Customer customer, Menu menu, Size size, int price) {
			this.customer = customer;
			this.menu = menu;
			this.size = size;
			this.price = price;
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
		public void receive(Drink drink) {
			System.out.println("[Customer] " + name + ": Received " + drink.label() + ". Thanks!");
		}
	}

	// 제조 결과물
	static class Drink {
		private final String label;

		public Drink(String label) {
			this.label = label;
		}

		public String label() {
			return label;
		}
	}

	// 캐셔는 바리스타에 대해 모르며 "작성(write)과 서빙(serve)"에 집중
	// SRP 적용: 캐셔는 주문지 작성과 서빙만 담당
	static class Cashier {
		private final Cafe cafe; // [소속 맥락] 출력/로깅 등에서 컨텍스트 표현
		private final Barista barista; // [협력자]

		public Cashier(Cafe cafe, Barista barista) {
			this.cafe = cafe;
			this.barista = barista;
		}

		// 주문지 작성: 가격은 메뉴에게 '시켜서' 얻고 OrderSlip에 확정 저장
		public OrderSlip writeOrderSlip(Customer customer, Menu menu, Size size) {
			int price = menu.price(size);
			OrderSlip orderSlip = new OrderSlip(customer, menu, size, price);
			return orderSlip;
		}

		// 제조 결과를 고객에게 전달(서빙)
		public void serve(Customer customer, Drink drink) {
			customer.receive(drink);
		}
	}

	// 다형성: 바리스타 역할을 인터페이스로 추상화 -> 구현을 자유롭게 교체 가능
	// DIP 적용: Cafe는 Barista 인터페이스에만 의존
	interface Barista {
		Drink prepare(OrderSlip orderSlip);
	}

	// [구현1] 사람 바리스타
	static class HumanBarista implements Barista {

		@Override
		public Drink prepare(OrderSlip orderSlip) {
			// 사람 바리스타 제조 과정...
			return new Drink(orderSlip.menu.name() + "(" + orderSlip.size + ")");
		}
	}

	// [구현2] 로봇 바리스타
	static class RobotBarista implements Barista {

		@Override
		public Drink prepare(OrderSlip orderSlip) {
			// 로봇 바리스타 제조 과정...
			return new Drink(orderSlip.menu.name() + "(" + orderSlip.size + ")");
		}
	}

	// [역할] '카페'라는 협력의 장소(경계/맥락)를 제공하는 역할.
	// [책임] 내부 구성원(Cashier, Barista)을 '소유·조립'하고, 고객 주문을 접수하여 적절한 협력으로 연결.
	// [협력] Customer -> Cafe(acceptOrder) -> Cashier -> Barista -> Cashier ->Customer 메시지 흐름의 시작점.
	static class Cafe {
		private final String name;
		private final Barista barista; // [협력자]
		private final Cashier cashier; // [협력자]

		// DIP: Cafe는 Barista의 책임에만 의존
		// 외부로부터 Barista 구체적인 객체를 주입 받음(의존성 주입)
		public Cafe(String name, Barista barista) {
			this.name = name;
			// [조립 책임] Cafe가 내부 객체를 생성/연결(Composition Root 관점)
			this.barista = barista;
			this.cashier = new Cashier(this, barista);
		}

		public String name() {
			return name;
		}

		// [요청/응답] Customer(sender)가 Cafe(receiver)에게 주문(acceptOrder) 메시지를 보냄.
		// Cafe는 이를 Cashier에게 위임하여 '협력'을 시작한다.
		// [Customer -> Cafe] 주문 접수 (요청/메시지). Cafe는 내부 협력을 오케스트레이션.
		public void acceptOrder(Customer customer, Menu menu, Size size) {
			OrderSlip orderSlip = cashier.writeOrderSlip(customer, menu, size);    // 주문지 작성 - 캐셔 책임
			Drink drink = barista.prepare(orderSlip);                              // 제조 - 바리스타 책임
			cashier.serve(customer, drink);                                          // 서빙 - 캐셔 책임
		}
	}

	// [시퀀스 요약]
	// Customer -> Cafe.acceptOrder(customer, menu, size)
	public static void main(String[] args) {
		Cafe humanCafe = new Cafe("Human Cafe", new HumanBarista());
		Customer alice = new Customer("Alice");
		humanCafe.acceptOrder(alice, Menu.AMERICANO, Size.SMALL);
		System.out.println();

		Cafe robotCafe = new Cafe("Robot Cafe", new RobotBarista());
		robotCafe.acceptOrder(alice, Menu.LATTE, Size.LARGE);
	}
}