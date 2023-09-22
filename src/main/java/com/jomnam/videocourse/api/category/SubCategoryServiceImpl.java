package com.jomnam.videocourse.api.category;

import org.springframework.stereotype.Service;

import com.jomnam.videocourse.exception.ResourceNotFoundExceptionHandler;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SubCategoryServiceImpl implements SubCategoryService{
	private final SubCategoryRepository subCategoryRepository;

	@Override
	public void create(SubCategory subCategory) {
		subCategoryRepository.save(subCategory);
	}

	@Override
	public void update(SubCategory subCategory) {
		subCategoryRepository.save(subCategory);
	}

	@Override
	public SubCategory getById(Long id) {
		return subCategoryRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundExceptionHandler("sub_category",id));
	}

	@Override
	public void remove(Long id) {
		SubCategory subCategory = getById(id);
		subCategoryRepository.delete(subCategory);;
	}

}
