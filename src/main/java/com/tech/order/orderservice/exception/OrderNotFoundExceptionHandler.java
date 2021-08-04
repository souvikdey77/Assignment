package com.tech.order.orderservice.exception;

import static java.time.LocalDateTime.now;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import java.util.LinkedHashMap;
import java.util.Map;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class OrderNotFoundExceptionHandler {

	@ExceptionHandler(value = OrderNotFoundException.class)
	public ResponseEntity<Object> orderItemNotFoundException(OrderNotFoundException ex, WebRequest request) {
		Map<String, Object> details = new LinkedHashMap<>();
		details.put("timestamp", now());
		details.put("message", ex.getMessage());
		return new ResponseEntity<>(details, NOT_FOUND);
	}
}
