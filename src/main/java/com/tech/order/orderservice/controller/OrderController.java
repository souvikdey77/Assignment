package com.tech.order.orderservice.controller;

import static org.springframework.http.HttpStatus.ACCEPTED;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.tech.order.orderservice.entity.Order;
import com.tech.order.orderservice.exception.OrderNotFoundException;
import com.tech.order.orderservice.impl.OrderServiceImpl;

/**
 * 
 * @author souvikdey
 *
 */
@RestController
@RequestMapping(value = "/ordermanagement")
public class OrderController {

	@Autowired
	private OrderServiceImpl service;

	@GetMapping(value = "/api/v1/orders/{orderId}")
	public ResponseEntity<Order> getOrderDetails(@PathVariable(required = true) int orderId) {
		Order order = service.getOrderDetails(orderId);
		return new ResponseEntity<Order>(order, OK);
	}

	@PostMapping(value = "/api/v1/order")
	public ResponseEntity<Integer> createOrder(@RequestBody @Valid Order order) {
		Integer orderId = service.createOrder(order);
		if (orderId == null) {
			throw new OrderNotFoundException("Same Order with orderid " + order.getOrderId() + "is already present");
		}
		return new ResponseEntity<Integer>(orderId, CREATED);
	}

	@DeleteMapping(value = "/api/v1/orders/{orderId}")
	public ResponseEntity<String> deleteOrder(@PathVariable(required = true) int orderId) {
		service.deleteOrder(orderId);
		return new ResponseEntity<String>("Order is successfully deleted", OK);
	}

	@PutMapping(value = "/api/v1/orders/{orderId}")
	public ResponseEntity<Order> updateOrder(@PathVariable(required = true) int orderId, @RequestBody Order order) {
		Order orderDetails = service.updateOrder(order, orderId);
		if (orderDetails == null) {
			throw new OrderNotFoundException(
					"Ordr with orderid " + order.getOrderId() + " is not present for an update!");
		}
		return new ResponseEntity<Order>(orderDetails, ACCEPTED);
	}
}
