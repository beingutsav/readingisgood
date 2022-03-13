package com.getir.readingisgoodservicerest.service;

import java.net.URI;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.getir.readingisgoodservicerest.controller.OrderDetailController;
import com.getir.readingisgoodservicerest.entity.OrderDetail;



@RestController
public class OrderService {
	
	@Autowired
	private OrderDetailController orderDetailControler;

	
	@GetMapping("/orders/{orderId}")
	public ResponseEntity<OrderDetail> retrieveOrderDetail(@PathVariable Long orderId) {
		final OrderDetail orderDetail =  orderDetailControler.findOrderById(orderId);
		
		return ResponseEntity.ok(orderDetail);
	}
	
	@GetMapping("/orders/{fromDate}/{toDate}")
	public ResponseEntity<List<OrderDetail>> retrieveOrderDetail(@PathVariable @DateTimeFormat(pattern = "yyyyMMdd") Date fromDate
			, @PathVariable  @DateTimeFormat(pattern = "yyyyMMdd") Date toDate) {
		final List<OrderDetail> orderDetails = orderDetailControler.findOrdersBetweenDates(fromDate, toDate);
		
		return ResponseEntity.ok(orderDetails);
	}
	
	@PostMapping("/customers/{customerId}/books/{bookId}/orders")
	public ResponseEntity<OrderDetail> addOrder(@PathVariable Long customerId, @PathVariable Long bookId, @Valid @RequestBody OrderDetail orderDetail) {
		
		OrderDetail persistedOrder = orderDetailControler.processOrder(customerId, bookId, orderDetail);
		
		URI location = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(persistedOrder.getId()).toUri();
		
		return ResponseEntity.created(location).build();
		
	}
	
}
