package com.getir.readingisgoodservicerest.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.getir.readingisgoodservicerest.entity.Book;


public interface BookRepository extends JpaRepository<Book, Long>{
	
}
