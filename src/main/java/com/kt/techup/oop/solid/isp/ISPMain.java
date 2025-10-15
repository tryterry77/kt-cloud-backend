package com.kt.techup.oop.solid.isp;

// ISP
// "클라이언트는 자신이 사용하지 않는 인터페이스에 의존하면 안 된다"
// 큰 인터페이스보다 작고 구체적인 여러 인터페이스가 낫다
// 인터페이스를 역할별로 분리

public class ISPMain {

	public static void main(String[] args) {
		// 불필요한 메서드를 강제로 구현
		// 인터페이스가 변경 시 사용하지 않는 클래스도 영향 받음
		Robot_BadCase robotBadCase = new Robot_BadCase();
		robotBadCase.work();

		// 필요한 인터페이스만 구현
		Robot robot = new Robot();
		robot.work();

		// 필요한 인터페이스만 구현
		Human human = new Human();
		human.work();
		human.eat();
		human.getSalary();
		human.attendMeeting();
		human.sleep();
	}
}
