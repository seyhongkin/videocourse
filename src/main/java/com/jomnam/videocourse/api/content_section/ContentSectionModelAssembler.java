package com.jomnam.videocourse.api.content_section;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.jomnam.videocourse.api.content.Content;
import com.jomnam.videocourse.api.content.ContentMapper;
import com.jomnam.videocourse.api.content.ContentRepository;
import com.jomnam.videocourse.api.content.web.ContentController;
import com.jomnam.videocourse.api.content.web.ContentDto;
import com.jomnam.videocourse.api.content_section.web.ContentSectionController;
import com.jomnam.videocourse.api.content_section.web.ContentSectionDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Component
@Configuration
public class ContentSectionModelAssembler extends RepresentationModelAssemblerSupport<ContentSection, EntityModel<ContentSectionDto>> {
    @Autowired
    private ContentSectionMapper contentSectionMapper;


    public ContentSectionMapper getContentSectionMapper() {
        return contentSectionMapper;
    }

    public ContentSectionModelAssembler() {
        super(ContentSectionController.class, (Class<EntityModel<ContentSectionDto>>) (Class<?>) EntityModel.class);
    }



    @Override
    public EntityModel<ContentSectionDto> toModel(ContentSection entity) {
        ContentSectionDto contentSectionDto = ContentSectionMapper.INSTANCE.entityToDto(entity);
        Link link = linkTo(methodOn(ContentSectionController.class).findAll()).withRel("findAll");
        contentSectionDto.setContent(toContentDto(entity.getContent()));
        return EntityModel.of(contentSectionDto,link);
    }

    @Override
    public CollectionModel<EntityModel<ContentSectionDto>> toCollectionModel(Iterable<? extends ContentSection> entities) {
        CollectionModel<EntityModel<ContentSectionDto>> collectionModel = super.toCollectionModel(entities);
        collectionModel.add(linkTo(methodOn(ContentSectionController.class).findAll()).withSelfRel());
        return collectionModel;
    }
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public List<Content> toContentDto(List<Content> content) {
        if (content.isEmpty())
            return Collections.emptyList();
        return content.stream()
                .map(content1 -> Content.builder()
                        .id(content1.getId())
                        .duration(content1.getDuration())
                        .type(content1.getType())
                        .link(content1.getLink())
                        .title(content1.getTitle())
                        .build()
                        .add(linkTo(
                                methodOn(ContentController.class)
                                        .findById(content1.getId()))
                                .withSelfRel()))
                .collect(Collectors.toList());
    }
}






