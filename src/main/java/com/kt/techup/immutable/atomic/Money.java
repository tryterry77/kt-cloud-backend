package com.kt.techup.immutable.atomic;

public class Money {
	final int amount;

	public Money(int amout) {
		this.amount = amout;
	}

	public int amount() {
		return amount;
	}

	// amount : 1000,
	// targetAMount : 2000
	public Money minus(int targetAmount) {
		if (this.amount < targetAmount)
			throw new IllegalArgumentException("잔액이 부족합니다.");

		return new Money(this.amount - targetAmount);
	}

	@Override
	public String toString() {
		return "Money(" + amount + ")";
	}
}
