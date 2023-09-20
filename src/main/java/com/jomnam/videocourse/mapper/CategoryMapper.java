package com.jomnam.videocourse.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.jomnam.videocourse.dto.CategoryDTO;
import com.jomnam.videocourse.entity.Category;

@Mapper(componentModel = "spring")
public interface CategoryMapper {
	CategoryMapper INSTANCE = Mappers.getMapper(CategoryMapper.class);
	
	Category toCategory(CategoryDTO dto);
}
