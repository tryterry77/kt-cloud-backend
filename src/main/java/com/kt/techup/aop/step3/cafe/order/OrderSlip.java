package com.kt.techup.aop.step3.cafe.order;

import com.kt.techup.aop.step3.cafe.Customer;
import com.kt.techup.aop.step3.product.Menu;
import com.kt.techup.aop.step3.product.Size;

// 주문지: 고객/메뉴/사이즈/확정 가격을 담아 "제조 의사소통"의 표준 포맷으로 사용
public class OrderSlip {
	private final Customer customer;
	private final Menu menu;
	private final Size size;
	private final int priceKRW;

	public OrderSlip(
		Customer customer, Menu menu,
		Size size, int priceKRW) {
		this.customer = customer;
		this.menu = menu;
		this.size = size;
		this.priceKRW = priceKRW;
	}

	public Menu menu() {
		return menu;
	}

	public Size size() {
		return size;
	}

	@Override
	public String toString() {
		return menu + " (" + size + "), " + priceKRW + " KRW";
	}
}
