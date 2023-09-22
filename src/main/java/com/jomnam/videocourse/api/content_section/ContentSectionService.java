package com.jomnam.videocourse.api.content_section;

import com.jomnam.videocourse.api.content.web.ContentCreateDto;
import com.jomnam.videocourse.api.content_section.web.ContentSectionCreate;
import com.jomnam.videocourse.api.content_section.web.ContentSectionDto;
import org.springframework.hateoas.CollectionModel;

public interface ContentSectionService {
    CollectionModel<ContentSectionDto> findAll();
    void createContentSection(ContentSectionCreate contentSectionCreate);
}
