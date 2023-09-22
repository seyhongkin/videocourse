package com.jomnam.videocourse.api.content_section.web;

import com.jomnam.videocourse.api.content.Content;
import com.jomnam.videocourse.api.course.Course;
import lombok.*;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Relation(collectionRelation = "Section", itemRelation = "contentsection")
public class ContentSectionDto {
    private Long id;
    private Course course;
    private String title;
    private List<Content> content;
}
