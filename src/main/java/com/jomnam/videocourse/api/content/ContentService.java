package com.jomnam.videocourse.api.content;

import com.jomnam.videocourse.api.content.web.ContentCreateDto;
import com.jomnam.videocourse.api.content.web.ContentDto;
import com.jomnam.videocourse.api.content.web.ContentUpdateDto;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;


public interface ContentService {
    ContentDto createContent(ContentCreateDto contentCreateDto);
    EntityModel<?> findContentById(Long id);
    void updateContentById(Long id, ContentUpdateDto contentUpdateDto);
    CollectionModel<?> findAll();
    void deleteById(Long id);
}
