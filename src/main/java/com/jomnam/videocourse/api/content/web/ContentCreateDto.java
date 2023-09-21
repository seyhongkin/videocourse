package com.jomnam.videocourse.api.content.web;

import com.jomnam.videocourse.api.content_section.ContentSection;
import lombok.Builder;

@Builder
public record ContentCreateDto(
        Long id,
        String title,
        String link,
        String type,
        Float duration,
        ContentSection contentSection
) {
}
