package com.getir.readingisgoodservicerest.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.getir.readingisgoodservicerest.controller.StatisticsController;
import com.getir.readingisgoodservicerest.model.CustomerMonthlyStatistics;


@RestController
public class StatisticsService {
	
	@Autowired
	private StatisticsController statisticsController;
	
	@GetMapping("/statistics/{customerId}")
	public ResponseEntity<List<CustomerMonthlyStatistics>> retrieveMonthlyStatisticsForCustomers(@PathVariable Long customerId) {
		List<CustomerMonthlyStatistics> customerMonthlyStatisticsLst = statisticsController.getCustomerMonthlyStatistics(customerId);
		
		return ResponseEntity.ok(customerMonthlyStatisticsLst);
	}
	
	
}
