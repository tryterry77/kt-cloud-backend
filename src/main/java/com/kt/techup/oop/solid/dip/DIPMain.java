package com.kt.techup.oop.solid.dip;

// DIP
// "구체적인 것이 아닌 추상적인 것에 의존해야 한다"
// 고수준 모듈은 저수준 모듈에 의존하면 안 됨
// 둘 다 추상화에 의존해야 함
public class DIPMain {
	public static void main(String[] args) {
		// MySQL 사용
		MySQLDatabase mySQLDatabase = new MySQLDatabase();
		UserService userService = new UserService(mySQLDatabase);
		userService.saveUser("User1");

		// PostgreSQL로 쉽게 변경(UserService 코드 수정 없음)
		PostgreSQLDatabase postgreSQLDatabase = new PostgreSQLDatabase();
		UserService userService2 = new UserService(postgreSQLDatabase);
		userService2.saveUser("User2");

		// DB 변경 시 UserService 수정 불필요
		// 유연하고 확장 가능
	}
}
