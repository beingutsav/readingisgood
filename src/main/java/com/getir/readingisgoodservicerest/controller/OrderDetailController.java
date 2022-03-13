package com.getir.readingisgoodservicerest.controller;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import com.getir.readingisgoodservicerest.dao.OrderDetailRepository;
import com.getir.readingisgoodservicerest.entity.Book;
import com.getir.readingisgoodservicerest.entity.Customer;
import com.getir.readingisgoodservicerest.entity.OrderDetail;
import com.getir.readingisgoodservicerest.exception.BookStockNotAvailableException;
import com.getir.readingisgoodservicerest.exception.OrderDetailNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class OrderDetailController{
	
	@Autowired
	private OrderDetailRepository orderDetailRepository;
	
	@Autowired
	private BookController bookController;
	
	@Autowired
	private CustomerController customerController;
	
	@Transactional(isolation = Isolation.REPEATABLE_READ)
	public OrderDetail processOrder(Long customerId, Long bookId, OrderDetail orderDetail) {
		
		final Customer customer = customerController.findById(customerId); 
		final Book book = bookController.findById(bookId);
		
		checkBookAvailability(orderDetail, book);
		
		bookController.reserveBook(book, orderDetail.getQuantity());

		orderDetail.setBook(book);
		orderDetail.setCustomer(customer);
		orderDetail.setTotalBill(book.getAmount().multiply(BigDecimal.valueOf(orderDetail.getQuantity())));
		
		return orderDetailRepository.save(orderDetail);
	}


	private void checkBookAvailability(OrderDetail orderDetail, final Book book) {
		if(book.getQuantity() < orderDetail.getQuantity()) {
			if(book.getQuantity() == 0) {
				throw new BookStockNotAvailableException("Stock for book - "+book.getName() + " is over, we will inform you via email when stock replenishes");
			}
			throw new BookStockNotAvailableException("Only "+book.getQuantity() + " of book + " +book.getName() + " are available for purchase");
		}
	}
	
	
	public OrderDetail findOrderById(final Long orderDetailId) {
		
		final Optional<OrderDetail> orderOpt = orderDetailRepository.findById(orderDetailId);
		if(orderOpt.isPresent())
			return orderOpt.get();
		
		throw new OrderDetailNotFoundException("Order id - "+orderDetailId);
		
		
	}
	
	public List<OrderDetail> findOrdersBetweenDates(final Date fromDate, final Date toDate) {
		final List<OrderDetail> orderDetails = orderDetailRepository.findBetweenDates(fromDate, toDate);
		if(orderDetails == null || orderDetails.isEmpty())
			throw new OrderDetailNotFoundException("No orders found bwtween "+fromDate + " and "+toDate);
		
		return orderDetails;
	}
	
	
}
