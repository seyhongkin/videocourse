package com.jomnam.videocourse.api.content.web;

import com.jomnam.videocourse.api.content_section.ContentSection;
import com.jomnam.videocourse.api.content_section.web.ContentSectionDto;
import lombok.*;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

@Relation(collectionRelation = "contents", itemRelation = "content")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class ContentDto extends RepresentationModel<ContentDto> {
    private Long id;
    private String title;
    private String link;
    private String type;
    private Float duration;
//        ContentSection contentSection
}
