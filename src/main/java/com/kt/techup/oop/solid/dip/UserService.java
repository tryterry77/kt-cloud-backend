package com.kt.techup.oop.solid.dip;

// 고수준 모듈(추상화에 의존)
public class UserService {
	private Database database;

	// 추상화에 의존
	public UserService(Database database) {
		this.database = database;
	}

	public void saveUser(String userData) {
		database.connect();
		database.saveData(userData);
	}
}
