package com.kt.techup.practice.lambda;

import java.util.Optional;

public class P1_Of_OfNullable {

	static String externalEmail(boolean asNull) {
		return asNull ? null : "user@example.com";
	}

	// (버그 유도) nulld일 때 of 사용 -> NPE
	static Optional<String> wrapEmail_BUGGY(String email) {
		// (예상) email이 null 이면 어떤 일이 발생?
		return Optional.of(email);
	}

	// TODO: null safe 버전 작성
	// (null 발생 시 , Null 이 아닌 다른 문자열로 변경하여 반환)
	static Optional<String> wrapEmail_SAFE(String email) {
		//TODO: email이 null 이면 "safe@example.com"으로 대체하여 Optional로 감싸 반환
		String safe = Optional.ofNullable(email).orElse("safe@example.com");
		return Optional.of(safe);
	}

	public static void main(String[] args) {
		System.out.println("== P1: of vs ofNullable ==");

		String notNullValue = externalEmail(false);
		String nullValue = externalEmail(true);
		System.out.println("nullValue = " + nullValue);
		System.out.println("wrapEmail_BUGGY(notNullValue) = " + wrapEmail_BUGGY(notNullValue)); // 예상

		try {
			System.out.println("wrapEmail_BUGGY(nullValue) = " + wrapEmail_BUGGY(nullValue));
		} catch (Exception e) {
			System.out.println("caught: " + e.getClass().getSimpleName());
		}

		// todo: NULL SAFE 버전으로 아래 출력이 이개대로 나오도록 구현
		System.out.println("wrapEmail_SAFE(notNullValue) = " + wrapEmail_SAFE(notNullValue));
		System.out.println("wrapEmail_SAFE(nullValue) = " + wrapEmail_SAFE(nullValue));
	}
}
