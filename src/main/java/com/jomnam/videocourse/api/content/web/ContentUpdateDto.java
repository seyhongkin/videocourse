package com.jomnam.videocourse.api.content.web;

public record ContentUpdateDto(
        String title,
        String link,
        String type,
        Float duration
) {
}
