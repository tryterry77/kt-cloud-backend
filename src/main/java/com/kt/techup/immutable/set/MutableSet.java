package com.kt.techup.immutable.set;

import java.util.HashSet;
import java.util.Set;

public class MutableSet {
	public static void main(String[] args) {
		Set<Person> people = new HashSet<>();
		Person p1 = new Person("John", 13);
		Person p2 = new Person("John", 13);

		people.add(p1);
		people.add(p2);

		System.out.println("people = " + people);
	}
}
