package com.kt.techup.oop.solid.isp;

// 사람은 모든 기능 필요
public class Human implements Workable, Eatable, Sleepable, Payable, MeetingAttendable {
	@Override
	public void eat() {
		System.out.println("사람은 먹는 중..");
	}

	@Override
	public void attendMeeting() {
		System.out.println("사람은 회의 참석 중..");
	}

	@Override
	public void getSalary() {
		System.out.println("사람은 샐러리 받는 중..");
	}

	@Override
	public void sleep() {
		System.out.println("사람은 자는 중..");
	}

	@Override
	public void work() {
		System.out.println("사람은 일 하는 중..₩");
	}
}
