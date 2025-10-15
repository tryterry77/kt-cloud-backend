package com.kt.techup.stream;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class StreamMain3 {
	public static void main(String[] args) {
		List<String> names = List.of("Alice", "Bob", "John", "David", "Eve", "elk");

		// 최종 연산 Collectors

		List<String> nameList = names.stream().toList();
		System.out.println("nameList = " + nameList);
		Set<String> nameSet = names.stream().collect(Collectors.toSet());
		System.out.println("nameSet = " + nameSet);
		Map<String, Integer> nameMap = names.stream().collect(Collectors.toMap(name -> name, String::length));
		System.out.println("nameMap = " + nameMap);

		// count
		long count = names.stream().count();
		System.out.println("count = " + count);
		long count2 = names.stream().count();
		System.out.println("count2 = " + count2);

		// joining
		// 배열을 스트링으로 처리할때 사용
		// 실제 웹 프레임워크에서 사용할때는 jaksonObjectMapper를 사용
		String joining = names.stream().collect(Collectors.joining(","));
		System.out.println("joining = " + joining);

		// 평균, 합계
		Integer length = names.stream().collect(Collectors.summingInt(s -> s.length()));
		System.out.println("length = " + length);

		Double lengthD = names.stream().collect(Collectors.averagingInt(s -> s.length()));
		System.out.println("lengthD = " + lengthD);

		// 평균 반올림
		Double collect = names.stream().collect(Collectors.averagingInt(s -> s.length()));
		double floor = Math.floor(collect);
		System.out.println("floor = " + floor);
		double ceil = Math.ceil(collect);
		System.out.println("ceil = " + ceil);
		long round = Math.round(collect);
		System.out.println("round = " + round);

		// 최대값, 최소값
		String s = names.stream()
			.sorted(Comparator.reverseOrder())
			.min(Comparator.comparing(String::length)).get();
		System.out.println("s = " + s);

		String s1 = names.stream()
			.max(Comparator.comparing(String::length)).get();
		System.out.println("s1 = " + s1);
	}
}
