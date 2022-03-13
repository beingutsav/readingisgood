package com.getir.readingisgoodservicerest.service;

import java.util.Date;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.getir.readingisgoodservicerest.exception.OrderDetailNotFoundException;
import com.getir.readingisgoodservicerest.model.ExceptionResponse;
import com.getir.readingisgoodservicerest.exception.NoSuchCustomerExistException;
import com.getir.readingisgoodservicerest.exception.CustomerAlreadyExistsException;



@ControllerAdvice
@RestController
public class ResponseExceptionHandler extends ResponseEntityExceptionHandler { 
	
	
	@ExceptionHandler({OrderDetailNotFoundException.class, NoSuchCustomerExistException.class})
	public final ResponseEntity<Object> handleNotExistsExceptions(Exception ex, WebRequest webRequest) {
		final ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), ex.getMessage(), webRequest.getDescription(false));
		return new ResponseEntity<Object>(exceptionResponse, HttpStatus.NOT_FOUND);
	}
	
	
	@ExceptionHandler(CustomerAlreadyExistsException.class)
	public final ResponseEntity<Object> handleCustomerExistsExceptions(Exception ex, WebRequest webRequest) {
		final ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), ex.getMessage(), webRequest.getDescription(false));
		return new ResponseEntity<Object>(exceptionResponse, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler({BadCredentialsException.class, UsernameNotFoundException.class})
	public final ResponseEntity<Object> handleAuthenticationExceptions(Exception ex, WebRequest webRequest) {
		final ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), ex.getMessage(), webRequest.getDescription(false));
		return new ResponseEntity<Object>(exceptionResponse, HttpStatus.UNAUTHORIZED);
	}
	
	
	@ExceptionHandler(Exception.class)
	public final ResponseEntity<Object> handleAllExceptions(Exception ex, WebRequest webRequest) {
		final ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), ex.getMessage(), webRequest.getDescription(false));
		return new ResponseEntity<Object>(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		final ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), "Validation Failed", ex.getBindingResult().toString());
		return new ResponseEntity<Object>(exceptionResponse, HttpStatus.BAD_REQUEST);
	}

}
