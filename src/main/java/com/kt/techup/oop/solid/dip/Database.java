package com.kt.techup.oop.solid.dip;

// Database를 추상화
public interface Database {

	void connect();

	void saveData(String userData);
}
