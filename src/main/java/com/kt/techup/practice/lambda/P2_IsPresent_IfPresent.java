package com.kt.techup.practice.lambda;

import java.util.Optional;

public class P2_IsPresent_IfPresent {
	static String externalName(boolean asNull) {
		return asNull ? null : "Alice";
	}

	static String greet_BUGGY(Optional<String> nameOpt) {
		if (nameOpt.isPresent()) {
			return "Hello, " + nameOpt.get();
		}
		return "Hi, " + nameOpt.get();
	}

	// todo: ifPresent 또는 ifPresentOrElse로 로그처리 + 기본값 반환
	static void printGreet_SAFE(Optional<String> nameOpt) {
		// 요구:
		// 1) 값이 있으면 System.out.println("[LOG] greet" + name);
		// 2) 값이 없으면 System.out.println("[LOG] greet null");
		// todo ..
		// nameOpt.ifPresentOrElse(name -> System.out.println("[LOG] greet" + name),
		// 	() -> System.out.println("[LOG] greet null"));
		if (nameOpt.isPresent()) {
			String name = nameOpt.get();
			System.out.println("[LOG] greet" + name);
		} else {
			System.out.println("[LOG] greet null");
		}
	}

	public static void main(String[] args) {
		System.out.println("== P2: isPresent vs ifPresent");
		Optional<String> nameNotNull = Optional.ofNullable(externalName(false));
		Optional<String> nameNull = Optional.ofNullable(externalName(true));

		System.out.println("greet_BUGGY(nameNotNull) = " + greet_BUGGY(nameNotNull));

		try {
			System.out.println("greet_BUGGY(nameNull) = " + greet_BUGGY(nameNull));
		} catch (Exception e) {
			System.out.println("e.getClass().getSimpleName() = " + e.getClass().getSimpleName());
		}

		// safe 검증
		System.out.println("\n-- SAFE --");
		printGreet_SAFE(nameNotNull);

		System.out.println("\n-- SAFE null --");
		printGreet_SAFE(nameNull);
	}
}
