package com.jomnam.videocourse.api.content_section.web;

import com.jomnam.videocourse.api.content.Content;
import com.jomnam.videocourse.api.course.Course;

import java.util.List;

public record ContentSectionCreate(
        List<Content> content,
        Course course,
        String title
) {
}
