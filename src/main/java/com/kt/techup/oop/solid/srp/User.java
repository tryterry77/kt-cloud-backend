package com.kt.techup.oop.solid.srp;

// SRP 옳은 예시
// 책임 분리

// 책임 1: 사용자 데이터 관리
public class User {
	private String name;
	private String email;

	public User(String name, String email) {
		this.name = name;
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public String getEmail() {
		return email;
	}
}

