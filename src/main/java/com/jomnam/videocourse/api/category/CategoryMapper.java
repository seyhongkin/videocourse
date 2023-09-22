package com.jomnam.videocourse.api.category;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.jomnam.videocourse.api.category.web.CategoryDTO;
import com.jomnam.videocourse.api.category.web.SubCategoryDTO;

@Mapper(componentModel = "spring")
public interface CategoryMapper {
	CategoryMapper INSTANCE = Mappers.getMapper(CategoryMapper.class);
	
	Category toCategory(CategoryDTO dto);
	
	@Mapping(target = "title", source = "dto.title")
	@Mapping(target = "description", source = "dto.description")
	SubCategory toSubCategory(SubCategoryDTO dto);
}
