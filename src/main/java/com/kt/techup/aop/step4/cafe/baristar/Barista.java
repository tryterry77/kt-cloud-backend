package com.kt.techup.aop.step4.cafe.baristar;

import com.kt.techup.aop.step4.cafe.order.OrderSlip;
import com.kt.techup.aop.step4.product.Drink;

public interface Barista {

	Drink prepare(OrderSlip slip);
}
