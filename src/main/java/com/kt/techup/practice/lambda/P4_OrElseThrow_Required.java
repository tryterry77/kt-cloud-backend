package com.kt.techup.practice.lambda;

import java.util.Optional;

public class P4_OrElseThrow_Required {
	static String externalToken(boolean asNull) {
		return asNull ? null : "token-xyz";
	}

	// (버그 유도) 나중에 터질 수 있는 코드
	static String useToken_BUGGY(String token) {
		// (예상) token이 null이면 어떤 일이?
		return token.toUpperCase();
	}

	// TODO: SAFE - orElseThrow로 null일 경우 IllegalArgumentException 발생,
	// null 아닐 경우 해당 문자열반환
	static String useToken_SAFE(String token) {
		// TODO
		return Optional.ofNullable(token).orElseThrow(() -> new IllegalArgumentException("token required"));
	}

	public static void main(String[] args) {
		System.out.println("== P4: orElseThrow ==");
		String tokenNotNull = externalToken(false);
		String tokenNull = externalToken(true);
		System.out.println("tokenNotNull(BUGGY) → " + useToken_BUGGY(tokenNotNull)); // (예상: ...)
		try {
			System.out.println("tokenNull(BUGGY) → " + useToken_BUGGY(tokenNull)); // (예상: ...)
		} catch (Exception e) {
			System.out.println("BUGGY caught: " + e.getClass().getSimpleName()); // (예상: ...)
		}
		// SAFE 검증
		System.out.println("tokenNotNull(SAFE) → " + useToken_SAFE(tokenNotNull)); // (예상: ...)
		try {
			System.out.println("tokenNull(SAFE) → " + useToken_SAFE(tokenNull)); // (예상: ...)
		} catch (Exception e) {
			System.out.println("SAFE caught: " + e.getMessage()); //(예상: ...)
		}
	}
}
