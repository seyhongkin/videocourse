package com.jomnam.videocourse.api.content_section.web;

import com.jomnam.videocourse.api.content.Content;
import com.jomnam.videocourse.api.course.Course;
import lombok.Builder;

import java.util.List;
@Builder
public record ContentSectionUpdateDto(
        String title,
        Course course,
        List<Content> content

        ) {
}
