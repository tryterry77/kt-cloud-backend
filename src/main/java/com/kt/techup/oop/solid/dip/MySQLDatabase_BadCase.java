package com.kt.techup.oop.solid.dip;

public class MySQLDatabase_BadCase {
	public void connect() {
		System.out.println("MySQL 연결");
	}

	public void saveData(String data) {
		System.out.println("데이터 저장: " + data);
	}
}
