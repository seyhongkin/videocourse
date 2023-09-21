package com.jomnam.videocourse.api.content;

import com.jomnam.videocourse.api.content.web.ContentCreateDto;
import com.jomnam.videocourse.api.content.web.ContentDto;
import com.jomnam.videocourse.api.content.web.ContentUpdateDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ContentServiceImpl implements ContentService {
    private final ContentRepository contentRepository;

    @Override
    public ContentDto createContent(ContentCreateDto contentCreateDto) {
        Content content = ContentMapper.INSTANCE.dtoCreateToEntity(contentCreateDto);
        Content saveContent = contentRepository.save(content);
        ContentDto contentDto = ContentMapper.INSTANCE.entityToDto(saveContent);
        return contentDto;
    }

    @Override
    public ContentDto findContentById(Long id) {
        Content content = contentRepository.findById(id).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,String.format("Not found your Id",id)));
        ContentDto contentDto = ContentMapper.INSTANCE.entityToDto(content);
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
    public List<ContentDto> findAll() {
        List<Content> list = contentRepository.findAll();
        List<ContentDto> content = ContentMapper.INSTANCE.dtoListToEntity(list);
        return content;
    }

    @Override
    public void deleteById(Long id) {
        findContentById(id);
        contentRepository.deleteById(id);
    }

}
