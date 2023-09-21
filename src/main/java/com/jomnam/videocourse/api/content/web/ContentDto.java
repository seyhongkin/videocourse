package com.jomnam.videocourse.api.content.web;

import lombok.Builder;

@Builder
public record ContentDto(
        String title,
        String link,
        String type,
        Float duration
) {
}
