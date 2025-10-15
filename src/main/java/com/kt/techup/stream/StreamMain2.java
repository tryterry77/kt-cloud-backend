package com.kt.techup.stream;

import java.util.List;

public class StreamMain2 {

	public static void main(String[] args) {
		List<String> names = List.of("Alice", "Bob", "John", "David", "Eve", "Bob", "Alice", "ss", "ff", "dd", "aa", "ee",
			"lkfd", ":sldkf", "sldkfj", "11", "22", "33", "44", "55)");
		List<String> names2 = List.of();
		// findFirst
		// Optional 반환
		// orElse, orElseGet, orElseThrow
		// ifPresent, ifPresentOrElse
		String first = names.stream().findFirst().get();

		String s1 = names.stream().findAny().get();
		System.out.println("s1 = " + s1);

		// allMatch, anyMatch, noneMatch
		// 보통 정규식과 함께 사용
		boolean alice = names.stream().allMatch(n -> n.equals("Alice"));
		System.out.println("alice = " + alice);
		boolean alice2 = names.stream().anyMatch(n -> n.equals("Alice"));
		System.out.println("alice2 = " + alice2);
		boolean alice3 = names.stream().noneMatch(n -> n.equals("Tom"));
		System.out.println("alice3 = " + alice3);

		// reduce
		// identity : 기준값 (어떤 값을 시작으로 reduce를 진행할지 설정)
		List<Integer> numbers = List.of(1, 2, 3, 4, 5);

		Integer i = numbers.stream().reduce(10, (x, y) -> x + y);
		System.out.println("i = " + i);
	}
}
