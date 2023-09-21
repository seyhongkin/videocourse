package com.jomnam.videocourse.api.content;

import com.jomnam.videocourse.api.category.CategoryMapper;
import com.jomnam.videocourse.api.content.web.ContentCreateDto;
import com.jomnam.videocourse.api.content.web.ContentDto;
import com.jomnam.videocourse.api.content.web.ContentUpdateDto;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ContentMapper {
    ContentMapper INSTANCE = Mappers.getMapper(ContentMapper.class);
    Content dtoCreateToEntity(ContentCreateDto contentCreateDto);
    ContentDto entityToDto(Content content);

    Content dtoToEntity(ContentDto contentDto);
    List<ContentDto> dtoListToEntity(List<Content> list);
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE) // use for ignore field in object.
    void mapForPartialUpdate(@MappingTarget Content content , ContentUpdateDto contentUpdateDto);


}
