package com.getir.readingisgoodservicerest.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.getir.readingisgoodservicerest.dao.BookRepository;
import com.getir.readingisgoodservicerest.entity.Book;
import com.getir.readingisgoodservicerest.exception.BookStockNotAvailableException;



@Service
public class BookController{
	
	@Autowired
	private BookRepository bookRepository;
	
	public Book findById(final Long id) {
		Optional<Book> optBook = bookRepository.findById(id);
		if(optBook.isPresent())
			return optBook.get();
	
		throw new BookStockNotAvailableException("Not available : Book - "+id);
	}
	
	public Book saveBook(final Book book) {
		return bookRepository.save(book);
	}
	
	public Book reserveBook(final Book book, final int quantity) {
		book.setQuantity(book.getQuantity() - quantity);
		return saveBook(book);
	}
	
	public Book updateQuantity(final Long bookId, final int quantity) {
		
		final Book persistedBook = findById(bookId);
		persistedBook.setQuantity(quantity);
		return saveBook(persistedBook);
		
	}
	
	
	
	
}
