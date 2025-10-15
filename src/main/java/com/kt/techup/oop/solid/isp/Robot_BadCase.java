package com.kt.techup.oop.solid.isp;

// 로봇은 먹고 자는 기능이 필요 없음!
// 불필요한 메서드를 강제로 구현해야 함
// 인터페이스 변경 시 사용하지 않는 클래스도 영향
// ISP 위배
public class Robot_BadCase implements Worker_BadCase {
	@Override
	public void work() {
		System.out.println("로봇 일하는 중..");
	}

	@Override
	public void eat() {
		throw new UnsupportedOperationException("로봇은 먹지 않음");
	}

	@Override
	public void sleep() {
		throw new UnsupportedOperationException("로봇은 자지 않음");
	}

	@Override
	public void getSalary() {
		throw new UnsupportedOperationException("지원하지 않음");
	}

	@Override
	public void attendMeeting() {
		throw new UnsupportedOperationException("지원하지 않음");
	}
}
