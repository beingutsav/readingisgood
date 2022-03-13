package com.getir.readingisgoodservicerest.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.getir.readingisgoodservicerest.entity.Customer;
import com.getir.readingisgoodservicerest.entity.OrderDetail;
import com.getir.readingisgoodservicerest.exception.OrderDetailNotFoundException;
import com.getir.readingisgoodservicerest.model.CustomerMonthlyStatistics;



@Service
public class StatisticsController{
	
	@Autowired
	private CustomerController customerController;
	
	public List<CustomerMonthlyStatistics> getCustomerMonthlyStatistics(final Long customerId) {
		
		final Customer customer = customerController.findById(customerId);
		
		if(customer.getOrderDetails() == null || customer.getOrderDetails().isEmpty())
			throw new OrderDetailNotFoundException("No Orders found for customer - "+customerId);
		
		final List<CustomerMonthlyStatistics> customerStatistics = getAggregatedStatistics(customer.getOrderDetails());
		
		return customerStatistics;
	}
	
	private List<CustomerMonthlyStatistics> getAggregatedStatistics(final List<OrderDetail> orderDetails) {

		return new ArrayList<CustomerMonthlyStatistics>(orderDetails.stream().collect(Collectors.toConcurrentMap(
				e -> e.getOrderDate().getMonth().toString(),
				v -> new CustomerMonthlyStatistics(v.getOrderDate().getMonth().toString(), v.getQuantity(), 1, v.getBook().getAmount()),
				CustomerMonthlyStatistics::merge)).values());
	}
	
}
