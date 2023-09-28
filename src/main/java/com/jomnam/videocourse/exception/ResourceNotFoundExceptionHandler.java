package com.jomnam.videocourse.exception;

import org.springframework.http.HttpStatus;

import lombok.RequiredArgsConstructor;


public class ResourceNotFoundExceptionHandler extends ResourceExceptionHandler{

	public ResourceNotFoundExceptionHandler(String entityName, long id) {
		super(HttpStatus.NOT_FOUND, String.format("%s id = %d is not found", entityName,id));
	}
	
}
