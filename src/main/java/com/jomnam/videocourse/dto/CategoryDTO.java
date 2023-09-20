package com.jomnam.videocourse.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CategoryDTO {
	@NotBlank(message = "Title is required")
	@Size(min = 2, message = "Title have to be longer than 2 characters")
	private String title;
	private String description;
}
