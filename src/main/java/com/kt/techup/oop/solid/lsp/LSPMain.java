package com.kt.techup.oop.solid.lsp;

// LSP
// "자식 클래스는 부모 클래스를 완전히 대체할 수 있어야 한다"
// 부모 타입의 객체를 자식 타입으로 바꿔도 프로그램이 정상 작동해야 한다
// is-a 관계가 논리적으로 성립해야 함
public class LSPMain {
	public static void main(String[] args) {

		// 부모 타입을 넣을 경우 정상 작동
		Car_BadCase normalCar = new Car_BadCase();
		drive(normalCar);

		// 자식 타입 넣을 경우 예외 발생할 수 있음
		// LSP 위반
		ElectricCar_BadCase electricCar = new ElectricCar_BadCase();
		drive(electricCar);

		// 자식 타입(GasolineCar)을 부모 타입(Car)으로 치환
		Car gasoline = new GasolineCar();
		drive(gasoline);

		// 자식 타입(ElectricCar)을 부모 타입(Car)으로 치환
		ElectricCar electricCar2 = new ElectricCar();
		drive(electricCar2);

		// 어떤 자식 타입을 넣어도 부모타입으로 치환되어 안전하게 수행
		// -> LSP 준수
	}

	private static void drive(Car car) {
		car.accelerate();
		car.accelerate();
		car.accelerate();
	}

	private static void drive(Car_BadCase car) {
		car.accelerate();
		car.accelerate();
		car.accelerate();
	}
}
