package com.kt.techup.aop.step3.util;

public class Util {
	public static final int MAX_RETRY_COUNT = 3;

	// 완료 로깅
	public static void LoggingCompleted(String methodName) {
		System.out.println("[Log] " + methodName + " completed.");
	}

	// 50% 확률로 예외를 발생하는 메서드
	public static void randomException(String methodName) {
		if (Math.random() < 0.5) {
			throw new RuntimeException("Random exception occurred in " + methodName);
		}
	}
}
