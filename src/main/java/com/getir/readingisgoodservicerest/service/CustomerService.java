package com.getir.readingisgoodservicerest.service;

import java.net.URI;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.getir.readingisgoodservicerest.controller.CustomerController;
import com.getir.readingisgoodservicerest.entity.Customer;
import com.getir.readingisgoodservicerest.model.CustomerOrder;



@RestController
public class CustomerService {
	
	@Autowired
	private CustomerController customerController;
	
	@PostMapping("/customers")
	public ResponseEntity<Object> addCustomer(@Valid @RequestBody Customer customer) {
		Customer persistedCustomer = customerController.addCustomer(customer);
		
		URI location = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(persistedCustomer.getId()).toUri();
		
		return ResponseEntity.created(location).build();
	}
	

	@GetMapping("/customers/{customerId}/orders")
	public ResponseEntity<CustomerOrder> retrieveAllOrdersForCustomers(@PathVariable Long customerId) {
		CustomerOrder customerOrder = customerController.retrieveCustomerOrderById(customerId);
		
		return ResponseEntity.ok(customerOrder);
	}
	
	
}
