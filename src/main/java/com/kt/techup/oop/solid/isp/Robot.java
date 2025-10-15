package com.kt.techup.oop.solid.isp;

// 로봇은 일하는 기능만 필요
// Workable 인터페이스만 구현
public class Robot implements Workable {
	@Override
	public void work() {
		System.out.println("로봇 일하는 중..");
	}
}
