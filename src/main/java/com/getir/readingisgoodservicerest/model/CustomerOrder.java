package com.getir.readingisgoodservicerest.model;


import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class CustomerOrder {
	
	
	private String customerName;
	private List<Order> orders;
	private BigDecimal totalAmountSpent;
	private int totalNoOfBooksOrdered;
	private LocalDate lastOrderedDate;
	
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public List<Order> getOrders() {
		return orders;
	}
	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}
	public BigDecimal getTotalAmountSpent() {
		return totalAmountSpent;
	}
	public void setTotalAmountSpent(BigDecimal totalAmountSpent) {
		this.totalAmountSpent = totalAmountSpent;
	}
	public int getTotalNoOfBooksOrdered() {
		return totalNoOfBooksOrdered;
	}
	public void setTotalNoOfBooksOrdered(int totalNoOfBooksOrdered) {
		this.totalNoOfBooksOrdered = totalNoOfBooksOrdered;
	}
	public LocalDate getLastOrderedDate() {
		return lastOrderedDate;
	}
	public void setLastOrderedDate(LocalDate lastOrderedDate) {
		this.lastOrderedDate = lastOrderedDate;
	}
	
	
}
