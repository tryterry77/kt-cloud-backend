package com.kt.techup.oop.solid.srp;

// 책임 2: 데이터 베이스 저장 전담
class UserRepository {
	public void save(User user) {
		System.out.println("User 데이터 저장: " + user.getName());
	}
}