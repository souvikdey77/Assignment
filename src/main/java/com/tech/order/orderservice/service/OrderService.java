package com.tech.order.orderservice.service;

import com.tech.order.orderservice.entity.Order;

public interface OrderService {
	
	public Order getOrderDetails(int orderId);
	
	public Integer createOrder(Order order);
	
	public void deleteOrder(int orderId);
	
	public Order updateOrder(Order order, int orderId);

}
