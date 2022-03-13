package com.getir.readingisgoodservicerest.entity;


import java.math.BigDecimal;
import java.time.LocalDate;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.validation.constraints.Min;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.annotations.ApiModelProperty;

@Entity(name = "ORDER_DETAIL")
public class OrderDetail {
	
	@Id
	@GeneratedValue
	private Long id;
	
	@CreationTimestamp
	private LocalDate orderDate;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JsonIgnore
	private Customer customer;
	
	@OneToOne
	@JoinColumn(name = "book_id", referencedColumnName = "id")
	private Book Book;
	
	private String orderDescription;
	
	@Min(value = 1, message = "Quantity should atleast be 1")
	@ApiModelProperty(value = "Quantity should atleast be 1")
	private int quantity;
	
	private BigDecimal totalBill;


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDate getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(LocalDate orderDate) {
		this.orderDate = orderDate;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Book getBook() {
		return Book;
	}

	public void setBook(Book book) {
		Book = book;
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

	public BigDecimal getTotalBill() {
		return totalBill;
	}

	public void setTotalBill(BigDecimal totalBill) {
		this.totalBill = totalBill;
	}
	
	
	
}
