package com.tech.order.orderservice.impl;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.tech.order.orderservice.entity.Order;
import com.tech.order.orderservice.entity.OrderItem;
import com.tech.order.orderservice.proxy.OrderItemProxy;
import com.tech.order.orderservice.repository.OrderRepository;
import com.tech.order.orderservice.service.OrderService;

/**
 * 
 * @author souvikdey
 *
 */
@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	private OrderItemProxy proxy;

	@Autowired
	private OrderRepository repository;

	@Override
	public Order getOrderDetails(int orderId) {
		Order orderDetails = new Order();
		Set<OrderItem> orderItems = new HashSet<>();
		Optional<Order> order = repository.findById(orderId);
		if (order.isPresent()) {
			Order newOrder = order.get();
			Set<OrderItem> setOfOrderItems = newOrder.getOrderItems();
			for (OrderItem input : setOfOrderItems) {
				ResponseEntity<OrderItem> responseOrder = proxy.getOrderItem(input.getProductCode());
				orderItems.add(responseOrder.getBody());
			}
			orderDetails.setCustomerName(order.get().getCustomerName());
			orderDetails.setOrderdate(order.get().getOrderdate());
			orderDetails.setOrderItems(order.get().getOrderItems());
			orderDetails.setShippingAddress(order.get().getShippingAddress());
			orderDetails.setTotal(order.get().getTotal());
		}

		return null;
	}

	@Override
	public Integer createOrder(Order order) {
		if (order != null) {
			Set<OrderItem> items = order.getOrderItems();
			ResponseEntity<String> response = proxy.createOrderItem(items);
			if (response.getStatusCode().is2xxSuccessful()) {
				return repository.save(order).getOrderId();
			}
		}
		return null;
	}

	@Override
	public Order updateOrder(Order order, int orderId) {
		Optional<Order> orderDetails = repository.findById(orderId);
		if (orderDetails.isPresent()) {
			Order newOrder = orderDetails.get();
			newOrder.setCustomerName(order.getCustomerName());
			newOrder.setOrderdate(order.getOrderdate());
			newOrder.setOrderItems(order.getOrderItems());
			newOrder.setShippingAddress(order.getShippingAddress());
			newOrder.setTotal(order.getTotal());
		}
		return null;
	}

	@Override
	public void deleteOrder(int orderId) {
		repository.deleteById(orderId);
	}

}
