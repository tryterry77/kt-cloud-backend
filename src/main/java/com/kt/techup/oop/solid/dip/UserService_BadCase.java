package com.kt.techup.oop.solid.dip;

// 고수준 모듈(UserService)가 저수준 모듈(MySQLDatabase)에 직접 의존
// DIP 위반
public class UserService_BadCase {
	private MySQLDatabase_BadCase database;

	public UserService_BadCase() {
		this.database = new MySQLDatabase_BadCase();
	}

	public void saveUser(String userData) {
		database.connect();
		database.saveData(userData);
	}
}
// PostgreSQL 로 바꿀경우 UserService 수정이 필요
// DIP 위반
