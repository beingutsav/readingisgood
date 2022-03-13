package com.getir.readingisgoodservicerest.dao;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.getir.readingisgoodservicerest.entity.OrderDetail;


public interface OrderDetailRepository extends JpaRepository<OrderDetail, Long>{
	
	@Query(value = "FROM ORDER_DETAIL WHERE ORDER_DATE BETWEEN :fromDate AND :toDate")
	List<OrderDetail> findBetweenDates(@Param("fromDate") Date fromDate, @Param("toDate") Date toDate);
	
}
