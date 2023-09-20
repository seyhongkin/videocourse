package com.jomnam.videocourse.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Entity
@Table(name = "contents")
public class Content {
	@Id
	@Column(name = "con_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Size(min = 5, message = "Title have to be longer than 2 characters")
	private String title;

	@Column(nullable = false)
	private String link;

	@Column(nullable = false)
	private String type;

	@Column(nullable = false)
	private float duration;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "cons_id")
	private ContentSection contentSection;
}
