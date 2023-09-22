package com.jomnam.videocourse.api.content;

import com.jomnam.videocourse.api.content.web.ContentController;
import com.jomnam.videocourse.api.content.web.ContentDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;


import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Configuration
@Component
public class ContentModelAssembler extends RepresentationModelAssemblerSupport<Content, EntityModel<ContentDto>> {

   private ContentMapper contentMapper;
    @Autowired
   public void setContentMapper(ContentMapper contentMapper){
       this.contentMapper = contentMapper;
   }


    public ContentModelAssembler() {
        super(ContentController.class,(Class<EntityModel<ContentDto>>) (Class<?>) EntityModel.class);
    }

    @Override
    public EntityModel<ContentDto> toModel(Content entity) {
        ContentDto contentDto = contentMapper.entityToDto(entity);
        Link link = linkTo(methodOn(ContentController.class).findContentAll()).withRel(IanaLinkRelations.CONTENTS);
        Link linkGetById = linkTo(methodOn(ContentController.class).findById(entity.getId())).withRel(IanaLinkRelations.SELF);
        return EntityModel.of(contentDto,link,linkGetById);
    }

    @Override
    public CollectionModel<EntityModel<ContentDto>> toCollectionModel(Iterable<? extends Content> entities) {
        return super.toCollectionModel(entities);
    }
}
