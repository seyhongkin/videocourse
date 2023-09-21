package com.jomnam.videocourse.api.content.web;

import lombok.Builder;

@Builder
public record ContentUpdateDto(
        String title,
        String link,
        String type,
        Float duration
) {
}
