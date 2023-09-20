package com.jomnam.videocourse.entity;

import java.math.BigDecimal;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Entity
@Table(name = "courses")
public class Course {
	@Id
	@Column(name = "course_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Size(min = 5, message = "Title have to be longer than 2 characters")
	private String title;
	
	@NotBlank(message = "language is required")
	@Column(nullable = false)
	private String language;
	
	@Column(nullable = false)
	private String requirement;
	
	private String description;
	
	@Column(nullable = false)
	private float accessDuration;
	
	@Column(nullable = false)
	private BigDecimal price;
	
	@JsonProperty("content_section")
	@OneToMany(mappedBy = "course")
	private List<ContentSection> contentSection;
}
