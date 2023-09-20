package com.jomnam.videocourse.api.category;

import com.jomnam.videocourse.api.category.web.CategoryDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface CategoryMapper {
	CategoryMapper INSTANCE = Mappers.getMapper(CategoryMapper.class);
	
	Category toCategory(CategoryDTO dto);
}
