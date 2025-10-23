package com.kt.techup.aop.step1.cafe.baristar;

import com.kt.techup.aop.step1.cafe.order.OrderSlip;
import com.kt.techup.aop.step1.product.Drink;

public interface Barista {
	// 주문지를 받아 제조하고 음료를 반환
	Drink prepare(OrderSlip slip);
}
