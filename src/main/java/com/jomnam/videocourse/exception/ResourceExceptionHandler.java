package com.jomnam.videocourse.exception;

import org.springframework.http.HttpStatus;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class ResourceExceptionHandler extends RuntimeException{
	private final HttpStatus status;
	private final String message;
}
