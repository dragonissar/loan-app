package com.devender.loan.error;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
public class CustomExceptionHandler{

	@ExceptionHandler(Exception.class)
	public final ResponseEntity<Object> handleGlobalException(Exception ex, WebRequest req) {
		ErrorDetails details= new ErrorDetails(req.getDescription(false), 
				ex.getMessage(), LocalDateTime.now());
		return new ResponseEntity<>(details,HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
