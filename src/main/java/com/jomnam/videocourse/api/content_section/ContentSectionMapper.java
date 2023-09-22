package com.jomnam.videocourse.api.content_section;

import com.jomnam.videocourse.api.content.web.ContentCreateDto;
import com.jomnam.videocourse.api.content_section.web.ContentSectionCreate;
import com.jomnam.videocourse.api.content_section.web.ContentSectionDto;
import com.jomnam.videocourse.api.content_section.web.ContentSectionUpdateDto;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ContentSectionMapper {
    ContentSectionMapper INSTANCE = Mappers.getMapper(ContentSectionMapper.class);
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void createNotLossData(@MappingTarget ContentSection contentSection , ContentSectionUpdateDto contentSectionUpdateDto);

    ContentSectionDto entityToDto(ContentSection contentSection);

    ContentSection dtoCreateToEntity(ContentSectionCreate contentSectionCreate);
}
