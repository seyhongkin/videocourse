package com.jomnam.videocourse.exception;

import java.time.LocalDateTime;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {
	
	@ExceptionHandler(ResourceNotFoundExceptionHandler.class)
	public final ResponseEntity<Object> handleResourceNotFoundMethodArgumentNotValid(Exception ex, WebRequest request) throws Exception {
		ErrorDetail  error = new ErrorDetail(LocalDateTime.now(), ex.getMessage(), request.getDescription(false));
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
	}
	
	
	@ExceptionHandler(DataIntegrityViolationException.class)
	public final ResponseEntity<Object> handleDuplicateEntryException(Exception ex, WebRequest request) throws Exception {
		String msg = ex.getMessage();
		if(ex.getMessage().contains("Unique index or primary key violation:"))
		{
			msg = "Duplicate data detected. The resource already exists.";
		}
			
		ErrorDetail  error = new ErrorDetail(LocalDateTime.now(), msg, request.getDescription(false));
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
	}
	
	@ExceptionHandler(Exception.class)
	public final ResponseEntity<Object> handleGlobalException(Exception ex, WebRequest request) throws Exception {
		ErrorDetail  error = new ErrorDetail(LocalDateTime.now(), ex.getMessage(), request.getDescription(false));
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
	}

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatusCode status, WebRequest request) {

		ErrorDetail error = new ErrorDetail(LocalDateTime.now(), ex.getFieldError().getDefaultMessage(),
				request.getDescription(false));	
		return ResponseEntity.status(status).body(error);
	}
}
