package com.getir.readingisgoodservicerest.controller;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.getir.readingisgoodservicerest.dao.CustomerRepository;
import com.getir.readingisgoodservicerest.entity.Customer;
import com.getir.readingisgoodservicerest.entity.OrderDetail;
import com.getir.readingisgoodservicerest.exception.CustomerAlreadyExistsException;
import com.getir.readingisgoodservicerest.exception.NoSuchCustomerExistException;
import com.getir.readingisgoodservicerest.exception.OrderDetailNotFoundException;
import com.getir.readingisgoodservicerest.model.CustomerOrder;
import com.getir.readingisgoodservicerest.model.Order;



@Service
public class CustomerController{
	
	@Autowired
	private CustomerRepository customerRepository;
	
	public Customer findById(final Long id) {
		
		final Optional<Customer> customerOpt = customerRepository.findById(id);
		if(customerOpt.isPresent()) 
			return customerOpt.get();
		
		throw new NoSuchCustomerExistException("No customer exists for id - "+id);
		
	}
	
	
	public Customer addCustomer(final Customer customer) {
		
		if(customerRepository.findCustomerByEmail(customer.getEmail()) != null)
			throw new CustomerAlreadyExistsException("Customer with email id "+customer.getEmail() + " is already registed");
			
		return customerRepository.save(customer);
	}
	
	
	public CustomerOrder retrieveCustomerOrderById(final Long customerId) {
		final Customer customer = findById(customerId);
		final CustomerOrder customerOrder = new CustomerOrder();
		customerOrder.setCustomerName(customer.getName());
		
		final List<OrderDetail> orderDetails = customer.getOrderDetails();
		
		if(orderDetails == null || orderDetails.isEmpty()) {
			throw new OrderDetailNotFoundException("No historical orders found for customer - "+customerId);
		}
		
		final List<Order> orders = new ArrayList<>();
		
		orderDetails.stream().forEach(orderDetail -> {
			orders.add(createOrderFromOrderDetail(orderDetail));
		});
		
		customerOrder.setOrders(orders);
		customerOrder.setTotalAmountSpent(getTotalAmountOfOrders(orderDetails));
		customerOrder.setTotalNoOfBooksOrdered(getTotalNoOfBooks(orderDetails));
		customerOrder.setLastOrderedDate(getLastOrderedDate(orderDetails));
		
		return customerOrder;
	}
	
	private Order createOrderFromOrderDetail(final OrderDetail orderDetail) {
		final Order order = new Order();
		order.setBookName(orderDetail.getBook().getName());
		order.setBookCost(orderDetail.getBook().getAmount());
		order.setOrderDate(orderDetail.getOrderDate());
		order.setOrderDescription(orderDetail.getOrderDescription());
		order.setQuantity(orderDetail.getQuantity());
		
		return order;
	}
	
	private BigDecimal getTotalAmountOfOrders(final List<OrderDetail> orderDetails) {
		return orderDetails.stream().map(orderDetail -> orderDetail.getTotalBill()).reduce(BigDecimal.ZERO, BigDecimal::add);
	}
	
	private int getTotalNoOfBooks(final List<OrderDetail> orderDetails) {
		return orderDetails.stream().map(orderDetail -> orderDetail.getQuantity()).reduce(0, Integer::sum);
	}
	private LocalDate getLastOrderedDate(final List<OrderDetail> orderDetails) {
		return orderDetails.stream().map(orderDetail -> orderDetail.getOrderDate()).max(Comparator.naturalOrder()).get();
	}
	
	
}
