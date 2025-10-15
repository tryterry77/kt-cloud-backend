package com.kt.techup.stream;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class StreamMain {

	/**
	 *
	 * <Stream>
	 *
	 * 1. 원본 데이터를 변경하지 않음
	 * 2. 재사용이 불가능
	 * 3. 내부 반복으로 작업을 처리
	 * 		반복문으로 처리. 순수 반복문 보다는 성능이 떨어져도 stream에서 제공하는 기능의 이점이 있음
	 *
	 * Stream 과정
	 *
	 * 스트림 생성 -> 중간 연산 -> 최종 연산 (3단계)
	 * ex) 객체.stream생성().중간연산().최종연산()
	 *
	 */

	/**
	 * Memo
	 * 함수에서 반환값으로 새로운 객체를 반환하는 로직은 불변성을 보장한다
	 *
	 */

	public static void main(String[] args) {
		List<String> names = List.of("Alice", "Bob", "John", "David", "Eve", "Bob", "Alice");
		// 알파벳 e가 들어간 이름만 모아서 출력

		// jdk 8
		var newNames = names.stream()
			.filter(name -> name.equals("Alice"))
			.collect(Collectors.toList());

		System.out.println("newNames = " + newNames);

		// jdk 11~
		// toList() 최종 연산 제공
		var newNames2 = names.stream()
			.filter(name -> name.contains("e"))
			.toList();

		System.out.println("newNames2 = " + newNames2);

		var newName3 = new ArrayList<String>();
		names.forEach(name -> {
			if (name.contains("e"))
				newName3.add(name);
		});
		System.out.println("newName3 = " + newName3);

		var mappedNames = names.stream()
			.map(String::toUpperCase)
			.toList();

		System.out.println("mappedNames = " + mappedNames);

		var userRequest = List.of(
			new UserCreateRequest("Alice", 2, LocalDate.now()),
			new UserCreateRequest("John", 20, LocalDate.now()),
			new UserCreateRequest("David", 10, LocalDate.now())
		);

		List<User> list = userRequest.stream()
			.filter(obj -> obj.age >= 2)
			.map(data -> new User(data.name, data.age, data.birthday))
			.toList();
		System.out.println("list = " + list);

		// flatMap
		// 2차원 배열일때 두개의 배열을 하나의 배열로 합치는 것
		var numbers = List.of(
			List.of(1, 2, 3),
			List.of(4, 5, 6),
			List.of(7, 8, 9)
		);

		List<Integer> integerStream = numbers.stream()
			.flatMap(Collection::stream)
			.toList();

		System.out.println("numbers = " + numbers);
		System.out.println("integerStream = " + integerStream);

		// limit
		List<String> list1 = names.stream()
			.limit(2)
			.toList();
		System.out.println("list1 = " + list1);

		// skip
		List<String> list2 = names.stream()
			.skip(2)
			.toList();

		System.out.println("list2 = " + list2);

		// sort
		// 실무에서는 디비에서 가져올떄 정렬되기 때문에 사용할일이 많진 않음
		List<String> list3 = names.stream()
			.sorted(Comparator.reverseOrder())
			.toList();
		System.out.println("list3 = " + list3);

		// foreach
		// 중간 연산으로 로그를 찍어보는 용도로 쓴다
		names.stream()
			.sorted(Comparator.reverseOrder())
			.forEach(System.out::println);

		// distinct 중복 제거
		List<String> list4 = names.stream()
			.distinct()
			.toList();

		System.out.println("list4 = " + list4);

		Set<String> collect = names.stream().collect(Collectors.toSet());
		System.out.println("collect = " + collect);

		//
	}
}
