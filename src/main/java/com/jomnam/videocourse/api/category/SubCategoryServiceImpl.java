package com.jomnam.videocourse.api.category;

import java.util.List;

import org.springframework.stereotype.Service;

import com.jomnam.videocourse.api.category.web.SubCategoryDTO;
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
	public void update(Long id, SubCategoryDTO dto) {
		SubCategory subCategory = getById(id);
		if(!dto.getTitle().isBlank()) {
			subCategory.setTitle(dto.getTitle());
		}
		if(!dto.getDescription().isBlank()) {
			subCategory.setDescription(dto.getDescription());
		}
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

	@Override
	public List<SubCategory> getByTitle(String title) {
		return subCategoryRepository.findByTitleContainingIgnoreCase(title);
	}

}
