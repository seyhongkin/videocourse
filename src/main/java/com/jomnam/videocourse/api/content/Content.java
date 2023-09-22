package com.jomnam.videocourse.api.content;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.jomnam.videocourse.api.content_section.ContentSection;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.springframework.hateoas.RepresentationModel;

@Getter
@Setter
@AllArgsConstructor
@Entity
@Table(name = "contents")
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Content extends RepresentationModel<Content> {
	@Id
	@Column(name = "con_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Size(min = 5, message = "Title have to be longer than 2 characters")
	private String title;

	@Column(nullable = false)
	private String link;

	@Column(nullable = false)
	private String type;

	@Column(nullable = false)
	private Float duration;
	
	@ManyToOne
	@JoinColumn(name = "cons_id")
	private ContentSection contentSection;


	public Content() {

	}
}
