package com.kt.techup.immutable.atomic;

public class ImmutableAtomic {

	public static void main(String[] args) {
		// 어떤 객체를 만들고 오류를 내보고 실패 원자성이 보장되는가?
		Money money1 = new Money(10_000);
		Money money2 = new Money(20_000);

		System.out.println("money1 = " + money1);

		try {
			Money changeMoney = money1.minus(5000);
			System.out.println("changeMoney = " + changeMoney);
		} catch (IllegalArgumentException e) {
			System.out.println("e = " + e);
		}
		System.out.println("money1 = " + money1);
	}
}
