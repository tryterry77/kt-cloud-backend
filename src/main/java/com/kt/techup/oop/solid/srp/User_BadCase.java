package com.kt.techup.oop.solid.srp;

// SRP 위배 예시
// 사용자 데이터 관리, 데이터베이스 저장, 이메일 발송을 모두 처리 - 3가지 채임을 가짐
public class User_BadCase {
	private String name;
	private String email;

	// 책임 1: 사용자 데이터 관리
	public void setName(String name) {
		this.name = name;
	}

	// 책임 2: 데이터베이스 저장
	public void saveToDatabase() {
		System.out.println("데이터베이스에 저장");
	}

	// 책침 3: 이메일 발송
	public void sendWelcomeEmail() {
		System.out.println("이메일 발송하기 : " + email);
	}
}

// 문제점
// DB 저장 방식이 바뀌면 User 클래스 수정
// 이메일 발송 방식이 바뀌면 User 클래스 수정
// 사용자 정보가 바뀌어도 User 클래스 수정
