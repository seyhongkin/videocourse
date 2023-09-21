package com.jomnam.videocourse.api.content;

import com.jomnam.videocourse.api.content.web.ContentCreateDto;
import com.jomnam.videocourse.api.content.web.ContentDto;
import com.jomnam.videocourse.api.content.web.ContentUpdateDto;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ContentServiceImpl implements ContentService {
    private final ContentRepository contentRepository;
    private final ContentModelAssembler contentModelAssembler;
    @Override
    public void createContent(ContentCreateDto contentCreateDto) {
        Content content = ContentMapper.INSTANCE.dtoCreateToEntity(contentCreateDto);
        Content saveContent = contentRepository.save(content);
        ContentDto contentDto = ContentMapper.INSTANCE.entityToDto(saveContent);
    }

    @Override
    public EntityModel<?> findContentById(Long id) {
        Content content = contentRepository.findById(id).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,String.format("Not found your Id",id)));
//        ContentDto contentDto = ContentMapper.INSTANCE.entityToDto(content);
       EntityModel<ContentDto> contentDto = contentModelAssembler.toModel(content);
        return contentDto;
    }

    @Override
    public void updateContentById(Long id, ContentUpdateDto contentUpdateDto) {
        findContentById(id);
        Content content = contentRepository.findById(id).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,String.format("Not found your Id",id)));
        ContentMapper.INSTANCE.mapForPartialUpdate(content,contentUpdateDto);
        contentRepository.save(content);
    }

    @Override
    public CollectionModel<?> findAll() {
        List<Content> list = contentRepository.findAll();

        return contentModelAssembler.toCollectionModel(list);
    }

    @Override
    public void deleteById(Long id) {
        findContentById(id);
        contentRepository.deleteById(id);
    }

}
