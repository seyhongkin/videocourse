package com.jomnam.videocourse.api.content_section;

import com.jomnam.videocourse.api.content_section.web.ContentSectionCreate;
import com.jomnam.videocourse.api.content_section.web.ContentSectionDto;
import com.jomnam.videocourse.api.content_section.web.ContentSectionUpdateDto;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;

public interface ContentSectionService {
    CollectionModel<ContentSectionDto> findAll();
    EntityModel<ContentSectionDto> findById(Long id);
    void createContentSection(ContentSectionCreate contentSectionCreate);
    void updateContentSection(Long id,ContentSectionUpdateDto contentSectionUpdateDto);
    void deleteContentSection(Long id);
}
