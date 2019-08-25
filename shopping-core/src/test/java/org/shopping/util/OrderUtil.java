package org.shopping.util;

import org.shopping.entity.Order;
import org.shopping.entity.OrderLine;

public class OrderUtil {

	public static Order createOrder(Long orderId, String userId) {
		Order order = new Order();
		order.setOrderId(orderId);
		order.setUserId(userId);
		return order;
	}

	public static OrderLine createOrderLine(Long code, String productCode, String productName, double cost, Order order) {
		OrderLine orderLine = new OrderLine();
		orderLine.setCode(code);
	//	orderLine.setOrder(order);
		orderLine.setCost(cost);
		orderLine.setProductCode(productCode);
		orderLine.setProductName(productName);
		return orderLine;
	}
}
