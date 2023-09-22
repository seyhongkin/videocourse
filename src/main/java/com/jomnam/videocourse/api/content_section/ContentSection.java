package com.jomnam.videocourse.api.content_section;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import com.jomnam.videocourse.api.content.Content;
import com.jomnam.videocourse.api.course.Course;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;

@Data
@Entity
@Table(name = "content_sections")
public class ContentSection {
	@Id
	@Column(name = "cons_id", nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Size(min = 5, message = "Title have to be longer than 2 characters")
	private String title;
	
	@ManyToOne
	@JoinColumn(name = "course_id")
	private Course course;
	
//	@JsonProperty(value = "contents")

	@OneToMany(mappedBy = "contentSection")
	@JsonIgnore
	private List<Content> content;


}
