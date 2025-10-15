package com.kt.techup.immutable.set;

import java.util.Objects;

public class Person {

	final String name;
	final int age;

	public Person(String name, int age) {
		this.name = name;
		this.age = age;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj instanceof Person p) {
			return this.name.equals(p.name) && this.age == p.age;
		}
		return false;
	}

	@Override
	public int hashCode() {
		return Objects.hash(name, age);
	}

	@Override
	public String toString() {
		return "Person{" +
			"name='" + name + '\'' +
			", age=" + age +
			'}';
	}
}
