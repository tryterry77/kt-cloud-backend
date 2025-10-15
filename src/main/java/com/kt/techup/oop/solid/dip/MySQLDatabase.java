package com.kt.techup.oop.solid.dip;

public class MySQLDatabase implements Database {
	@Override
	public void connect() {
		System.out.println("MySQL 연결..");
	}

	@Override
	public void saveData(String userData) {
		System.out.println("MySQL에 데이터 저장");
	}
}
