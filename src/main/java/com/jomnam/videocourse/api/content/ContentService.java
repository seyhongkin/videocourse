package com.jomnam.videocourse.api.content;

import com.jomnam.videocourse.api.content.web.ContentCreateDto;
import com.jomnam.videocourse.api.content.web.ContentDto;
import com.jomnam.videocourse.api.content.web.ContentUpdateDto;

import java.util.List;

public interface ContentService {
    ContentDto createContent(ContentCreateDto contentCreateDto);
    ContentDto findContentById(Long id);
    void updateContentById(Long id, ContentUpdateDto contentUpdateDto);
    List<ContentDto> findAll();
    void deleteById(Long id);
}
