package com.kt.techup.practice.lambda;

import java.util.Optional;
import java.util.function.Supplier;

public class P_OrElse_Vs_OrElseGet_Timing {
	public static void main(String[] args) {
		System.out.println("== orElse vs orElseGet: 평가 시점 비교 ==");
		Optional<String> present = Optional.of("VALUE");
		Optional<String> empty = Optional.empty();
		// 기본값 생성기: 호출될 때만 로그 출력
		Supplier<String> makeDefault = () -> {
			System.out.println("makeDefault.get() 호출됨");
			return "DEFAULT";
		};
		// A. orElse: 인자를 '먼저' 만들어서 넘김
		System.out.println("-- orElse --");
		System.out.println("present → " + present.orElse(makeDefault.get())); //(예상: 호출 여부/결과?)
		System.out.println("empty → " + empty.orElse(makeDefault.get())); //(예상: 호출 여부/결과?)
		// B. orElseGet: 필요할 때만 Supplier 실행
		System.out.println("-- orElseGet --");
		System.out.println("present → " + present.orElseGet(makeDefault)); // (예상: 호출 여부/결과?)
		System.out.println("empty → " + empty.orElseGet(makeDefault)); // (예상: 호출 여부/결과?)
	}
}
