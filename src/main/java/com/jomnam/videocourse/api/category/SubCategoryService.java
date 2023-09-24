package com.jomnam.videocourse.api.category;

import java.util.List;

import com.jomnam.videocourse.api.category.web.SubCategoryDTO;

public interface SubCategoryService {
	void create(SubCategory subCategory);
	void update(Long id, SubCategoryDTO dto);
	SubCategory getById(Long id);
	void remove(Long id);
	List<SubCategory> getByTitle(String title);
}
