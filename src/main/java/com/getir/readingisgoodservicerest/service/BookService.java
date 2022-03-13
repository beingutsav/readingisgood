package com.getir.readingisgoodservicerest.service;

import java.net.URI;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.getir.readingisgoodservicerest.controller.BookController;
import com.getir.readingisgoodservicerest.entity.Book;


@RestController
public class BookService {
	
	@Autowired
	private BookController bookController;
	
	@PostMapping("/books")
	public ResponseEntity<Object> addBook(@Valid @RequestBody Book book) {
		Book persistedBook = bookController.saveBook(book);
		
		URI location = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(persistedBook.getId()).toUri();
		
		return ResponseEntity.created(location).build();
	}
	
	@PutMapping("/books/{bookId}/{bookQty}")
	public ResponseEntity<Book> updateBook(@PathVariable Long bookId, @PathVariable int bookQty) {
		Book updatedBook = bookController.updateQuantity(bookId, bookQty);
		
		return ResponseEntity.ok(updatedBook);
	}
	
	
}
