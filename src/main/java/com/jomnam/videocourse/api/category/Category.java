package com.jomnam.videocourse.api.category;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Entity
@Table(name = "categories")
public class Category {
	@Id
	@Column(name = "cate_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(unique = true)
	@Size(min = 2, message = "Title have to be longer than 2 characters")
	private String title;
	private String description;
	@JsonIgnore
	@JsonProperty("sub_categories")
	@OneToMany(mappedBy = "category")
	private List<SubCategory> subCategories;
}
