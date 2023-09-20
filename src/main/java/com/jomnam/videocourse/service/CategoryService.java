package com.jomnam.videocourse.service;

import java.util.List;

import com.jomnam.videocourse.dto.CategoryDTO;
import com.jomnam.videocourse.entity.Category;
import com.jomnam.videocourse.entity.SubCategory;

public interface CategoryService {
	List<Category> getAll();
	void create(Category category);
	Category getById(long id);
	void update(long id, CategoryDTO dto);
	List<SubCategory> getSubCategories(long id);
}
