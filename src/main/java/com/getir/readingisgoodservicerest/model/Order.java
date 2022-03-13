package com.getir.readingisgoodservicerest.model;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Order {
	
	private String bookName;
	private BigDecimal bookCost;
	private LocalDate orderDate;
	private String orderDescription;
	private int quantity;
	
	
	public String getBookName() {
		return bookName;
	}
	public void setBookName(String bookName) {
		this.bookName = bookName;
	}
	public BigDecimal getBookCost() {
		return bookCost;
	}
	public void setBookCost(BigDecimal bookCost) {
		this.bookCost = bookCost;
	}
	public LocalDate getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(LocalDate orderDate) {
		this.orderDate = orderDate;
	}
	public String getOrderDescription() {
		return orderDescription;
	}
	public void setOrderDescription(String orderDescription) {
		this.orderDescription = orderDescription;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	

}
