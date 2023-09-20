package com.jomnam.videocourse.api.category.web;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jomnam.videocourse.api.category.Category;
import com.jomnam.videocourse.api.category.CategoryMapper;
import com.jomnam.videocourse.api.category.CategoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jomnam.videocourse.api.category.SubCategory;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/category")
public class CategoryController {
	private final CategoryService categoryService;

	@GetMapping
	public ResponseEntity<?> getAll() {
		List<Category> categories = categoryService.getAll();
		return ResponseEntity.ok(categories);
	}
	
	@GetMapping("{id}")
	public ResponseEntity<?> getById(@PathVariable("id") long id) {
		return ResponseEntity.ok(categoryService.getById(id));
	}
	
	@GetMapping("{id}/sub")
	public ResponseEntity<?> getSubCategories(@PathVariable("id") long id) {
		List<SubCategory> subCategories = categoryService.getSubCategories(id);
		
		Map<String, Object> response = new HashMap<>();
		response.put("timestamp", LocalDateTime.now());
		response.put("count", subCategories.size());
		response.put("sub_categories", subCategories);
		return ResponseEntity.ok(response);
	}
	
	
	@PostMapping
	public ResponseEntity<?> create(@Valid @RequestBody CategoryDTO dto) {
		Category category = CategoryMapper.INSTANCE.toCategory(dto);
		categoryService.create(category);
		return ResponseEntity.ok().build();
	}
	
	
	@PutMapping("{id}")
	public ResponseEntity<?> update(@PathVariable("id") long id, @Valid @RequestBody CategoryDTO dto) {
		categoryService.update(id, dto);
		return ResponseEntity.ok().build();
	}
	
}
