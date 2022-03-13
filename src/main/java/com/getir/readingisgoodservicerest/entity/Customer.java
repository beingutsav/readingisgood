package com.getir.readingisgoodservicerest.entity;


import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.annotations.ApiModelProperty;

@Entity(name = "CUSTOMER")
public class Customer {
	
	@Id
	@GeneratedValue
	private Long id;
	
	@Size(min = 2, message = "Name should have atleast 2 characters")
	@ApiModelProperty(value = "Name should have atleast 2 characters")
	private String name;
	
	@Email(message = "Please enter valid email address")
	@ApiModelProperty(value = "It should be a valid email address")
	private String email;
	
	@OneToMany(mappedBy = "customer")
	@JsonIgnore
	private List<OrderDetail> orderDetails; 
	
	
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
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public List<OrderDetail> getOrderDetails() {
		return orderDetails;
	}
	public void setOrderDetails(List<OrderDetail> orderDetails) {
		this.orderDetails = orderDetails;
	}
	
	
	

}
