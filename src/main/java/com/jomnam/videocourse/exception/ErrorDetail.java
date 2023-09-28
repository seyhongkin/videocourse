package com.jomnam.videocourse.exception;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ErrorDetail {
	private LocalDateTime timestamp;
	private String message;
	private String details;
}
