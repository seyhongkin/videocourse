package com.jomnam.videocourse.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.jomnam.videocourse.dto.CategoryDTO;
import com.jomnam.videocourse.entity.Category;
import com.jomnam.videocourse.entity.SubCategory;
import com.jomnam.videocourse.exception.ResourceNotFoundExceptionHandler;
import com.jomnam.videocourse.repository.CategoryRepository;
import com.jomnam.videocourse.service.CategoryService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
	private final CategoryRepository categoryRepository;

	@Override
	public List<Category> getAll() {
		return categoryRepository.findAll();
	}

	@Override
	public void create(Category category) {
		categoryRepository.save(category);
	}

	@Override
	public Category getById(long id) {
		return categoryRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundExceptionHandler("Category", id));
	}

	@Override
	public List<SubCategory> getSubCategories(long id) {
		Category category = categoryRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundExceptionHandler("Category", id));
		
		return category.getSubCategories();
	}
	
	@Override
	public void update(long id, CategoryDTO dto) {
		Category category = getById(id);
		if (!dto.getDescription().isBlank()) {
			category.setDescription(dto.getDescription());
		}
		if (!dto.getTitle().isBlank()) {
			category.setTitle(dto.getTitle());
		}
		categoryRepository.save(category);
	}

}
