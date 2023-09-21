package com.jomnam.videocourse.api.content.web;

import lombok.Builder;

@Builder
public record ContentCreateDto(
        Long id,
        String title,
        String link,
        String type,
        Float duration
) {
}
