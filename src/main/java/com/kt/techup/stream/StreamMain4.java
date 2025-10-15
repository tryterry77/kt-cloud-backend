package com.kt.techup.stream;

import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class StreamMain4 {
	public static void main(String[] args) {
		List<String> names = List.of("Alice", "Bob", "John", "David", "Eve", "elk", "Bob");
		Map<String, List<String>> collect = names.stream()
			.collect(Collectors.groupingBy(n -> n));
		System.out.println("collect = " + collect);

		// Exam
		List<Exam> exams = List.of(
			new Exam("세현", 10),
			new Exam("Tom", 20),
			new Exam("David", 30),
			new Exam("Bob", 40),
			new Exam("Cat", 50),
			new Exam("Dog", 20),
			new Exam("Floor", 30),
			new Exam("Elephant", 20),
			new Exam("Goorm", 70),
			new Exam("High", 100),
			new Exam("Ice", 30)
		);

		List<Exam> list = exams.stream()
			.sorted((s1, s2) -> s2.score - s1.score)
			.toList();
		System.out.println("list = " + list);

		// groupingBy
		Map<Integer, List<Exam>> groupedMap = exams.stream().collect(Collectors.groupingBy(s -> s.score));

		System.out.println("groupedMap = " + groupedMap);

		Map<Integer, List<Exam>> collect1 = exams.stream()
			.collect(Collectors.groupingBy(s -> s.score, Collectors.toList()));

		System.out.println("collect1 = " + collect1);

		// partitioningBy
		// 특정 값을 기준으로 true, false로 partition
		Map<Boolean, List<Exam>> partitionedMap = exams.stream().collect(
			Collectors.partitioningBy(exam -> exam.score >= 60)
		);

		List<Exam> passedPerson = partitionedMap.entrySet().stream()
			.filter(entry -> entry.getKey())
			.flatMap(map -> map.getValue().stream())
			.map(exam -> {
				exam.isPass = true;
				return exam;
			})
			.toList();

		System.out.println("passedPerson = " + passedPerson);

		// summarizing
		// 통계 값을 요약해서 보여줄때 (count, sum, min, average, max)
		IntSummaryStatistics collect2 = names.stream()
			.collect(Collectors.summarizingInt(String::length));
		System.out.println("collect2 = " + collect2);
	}
}
