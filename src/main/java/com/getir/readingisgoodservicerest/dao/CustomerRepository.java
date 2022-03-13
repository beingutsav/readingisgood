package com.getir.readingisgoodservicerest.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.getir.readingisgoodservicerest.entity.Customer;


public interface CustomerRepository extends JpaRepository<Customer, Long>{
	
	@Query(value = "FROM CUSTOMER WHERE EMAIL = :email")
	Customer findCustomerByEmail(@Param("email") String email);
	
}
