package com.jomnam.videocourse.api.category;

import java.util.List;

import com.jomnam.videocourse.api.category.web.CategoryDTO;

public interface CategoryService {
	List<Category> getAll();
	void create(Category category);
	Category getById(long id);
	void update(long id, CategoryDTO dto);
	List<SubCategory> getSubCategories(long id);
}
