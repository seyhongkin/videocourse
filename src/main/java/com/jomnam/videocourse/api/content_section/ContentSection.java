package com.jomnam.videocourse.api.content_section;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import com.jomnam.videocourse.api.content.Content;
import com.jomnam.videocourse.api.course.Course;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Entity
@Table(name = "content_sections")
public class ContentSection {
	@Id
	@Column(name = "cons_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Size(min = 5, message = "Title have to be longer than 2 characters")
	private String title;
	
	@ManyToOne
	@JoinColumn(name = "course_id")
	private Course course;
	
	@JsonProperty("contents")
	@OneToMany(mappedBy = "contentSection")
	private List<Content> content;
}
