package com.kt.techup.oop.solid.dip;

public class PostgreSQLDatabase implements Database {
	@Override
	public void connect() {
		System.out.println("PostgreSQL 연결..");
	}

	@Override
	public void saveData(String userData) {
		System.out.println("PostgreSQL에 데이터 저장");
	}
}
