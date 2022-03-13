package com.getir.readingisgoodservicerest.entity;


import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;


@Entity
public class Book {
	
	@Id
	@GeneratedValue
	private Long id;
	
	@NotBlank(message = "Book name can not be blank")
	private String name;
	
	@Min(value = 0, message = "Book amount can not be zero")
	private BigDecimal amount;
	
	@Min(value = 1, message = "Quantity should atleast be 1 for book")
	private int quantity;
	
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}	

}
