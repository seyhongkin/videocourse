package com.jomnam.videocourse.api.category;

public interface SubCategoryService {
	void create(SubCategory subCategory);
	void update(SubCategory subCategory);
	SubCategory getById(Long id);
	void remove(Long id);
}
