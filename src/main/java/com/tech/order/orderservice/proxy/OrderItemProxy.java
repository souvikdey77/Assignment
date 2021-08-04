package com.tech.order.orderservice.proxy;

import java.util.Set;
import javax.validation.Valid;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.tech.order.orderservice.entity.OrderItem;

@FeignClient(name = "OrderItemService", url = "localhost:8081")
public interface OrderItemProxy {

	@GetMapping(value = "/api/v1/orderitem/{productId}")
	public ResponseEntity<OrderItem> getOrderItem(@PathVariable("productId") int productId);

	@PostMapping(value = "/api/v1/orderItem")
	public ResponseEntity<String> createOrderItem(@RequestBody @Valid Set<OrderItem> items);

	@PutMapping(value = "/api/v1/updateItem/{productId}")
	public ResponseEntity<Integer> updateOrderItem(@RequestBody @Valid OrderItem orderItem,
			@PathVariable int productId);

	@DeleteMapping(value = "/api/v1/{productId}")
	public void deleteOrderItem(@PathVariable(required = true) int productId);

}
