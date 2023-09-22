package com.jomnam.videocourse.api.category.web;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jomnam.videocourse.api.category.Category;
import com.jomnam.videocourse.api.category.CategoryMapper;
import com.jomnam.videocourse.api.category.CategoryService;
import com.jomnam.videocourse.api.category.SubCategory;
import com.jomnam.videocourse.api.category.SubCategoryService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/category")
public class CategoryController {
	private final CategoryService categoryService;
	private final SubCategoryService subCategoryService;

	@GetMapping
	public ResponseEntity<?> getAll() {
		List<Category> categories = categoryService.getAll();
		List<EntityModel<Category>> categoryLinks = categories.stream()
			.map(c -> EntityModel.of(c, linkTo(methodOn(this.getClass()).getById(c.getId())).withRel("_self")))
			.collect(Collectors.toList());
		
		return ResponseEntity.ok(categoryLinks);
	}
	
	@GetMapping("{id}")
	public ResponseEntity<?> getById(@PathVariable("id") long id) {
		Category category = categoryService.getById(id);
		EntityModel<Category> entityModel = EntityModel.of(category);
		WebMvcLinkBuilder link = linkTo(methodOn(this.getClass()).getSubCategories(id));
		entityModel.add(link.withRel("_subcategories"));
		
		return ResponseEntity.ok(entityModel);
	}
	
	@GetMapping("{id}/sub")
	public ResponseEntity<?> getSubCategories(@PathVariable("id") long id) {
		List<SubCategory> subCategories = categoryService.getSubCategories(id);
		List<EntityModel<SubCategory>> subCategoryLinks = subCategories.stream()
		.map(sc -> EntityModel
				.of(sc, linkTo(methodOn(this.getClass()).getSubById(sc.getId())).withSelfRel()))
		.collect(Collectors.toList());
		
		
		Map<String, Object> response = new HashMap<>();
		response.put("timestamp", LocalDateTime.now());
		response.put("count", subCategories.size());
		response.put("sub_categories", subCategoryLinks);
		return ResponseEntity.ok(response);
	}
	
	
	@PostMapping
	public ResponseEntity<?> create(@Valid @RequestBody CategoryDTO dto) {
		Category category = CategoryMapper.INSTANCE.toCategory(dto);
		categoryService.create(category);
		return ResponseEntity.ok().build();
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity<?> remove(@PathVariable long id) {
		categoryService.remove(id);
		return ResponseEntity.ok().build();
	}
	
	
	@PutMapping("{id}")
	public ResponseEntity<?> update(@PathVariable("id") long id, @Valid @RequestBody CategoryDTO dto) {
		categoryService.update(id, dto);
		return ResponseEntity.ok().build();
	}
	
	@PostMapping("{id}")
	public ResponseEntity<?> createSub(@PathVariable Long id, @Valid @RequestBody SubCategoryDTO dto) {
		SubCategory subCategory = CategoryMapper.INSTANCE.toSubCategory(dto);
		Category category = new Category();
		category.setId(id);
		
		subCategory.setCategory(category);
		subCategoryService.create(subCategory);
		return ResponseEntity.ok().build();
	}
	
	@PutMapping("sub/{id}")
	public ResponseEntity<?> updateSub(@PathVariable long id, @RequestBody SubCategoryDTO dto) {
		SubCategory subCategory = subCategoryService.getById(id);
		subCategory.setTitle(dto.getTitle());
		subCategory.setDescription(dto.getDescription());
		
		subCategoryService.update(subCategory);
		return ResponseEntity.ok().build();
	}
	
	@GetMapping("sub/{id}")
	public ResponseEntity<?> getSubById(@PathVariable long id) {
		SubCategory subCategory = subCategoryService.getById(id);
		long categoryId = subCategory.getCategory().getId();
		
		EntityModel<SubCategory> entityModel = EntityModel.of(subCategory);
		WebMvcLinkBuilder linkBuilder = linkTo(methodOn(this.getClass()).getSubCategories(categoryId));
		entityModel.add(linkBuilder.withRel("_section"));
		return ResponseEntity.ok(entityModel);
	}
	
	@DeleteMapping("sub/{id}")
	public ResponseEntity<?> deleteSub(@PathVariable long id) {
		subCategoryService.remove(id);
		return ResponseEntity.ok().build();
	}
}
