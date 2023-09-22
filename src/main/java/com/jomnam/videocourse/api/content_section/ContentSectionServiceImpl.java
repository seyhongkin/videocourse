package com.jomnam.videocourse.api.content_section;

import com.jomnam.videocourse.api.content_section.web.ContentSectionCreate;
import com.jomnam.videocourse.api.content_section.web.ContentSectionDto;
import com.jomnam.videocourse.api.content_section.web.ContentSectionUpdateDto;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ContentSectionServiceImpl implements ContentSectionService {
    private final ContentSectionModelAssembler contentSectionModelAssembler;
    private final ContentSectionRepository contentSectionRepository;

    @Override
    public CollectionModel<ContentSectionDto> findAll() {
        List<ContentSection> contentSections = contentSectionRepository.findAll();
        CollectionModel collectionModel = contentSectionModelAssembler.toCollectionModel(contentSections);
        return CollectionModel.of(collectionModel);
    }

    @Override
    public EntityModel<ContentSectionDto> findById(Long id) {
        ContentSection contentSection = contentSectionRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "not found id" + id));
        return contentSectionModelAssembler.toModel(contentSection);
    }

    @Override
    public void createContentSection(ContentSectionCreate contentSectionCreate) {
        ContentSection contentSection = ContentSectionMapper.INSTANCE.dtoCreateToEntity(contentSectionCreate);
        contentSectionRepository.save(contentSection);
    }

    @Override
    public void updateContentSection(Long id,ContentSectionUpdateDto contentSectionUpdateDto) {
        findById(id);
        ContentSection contentSection = contentSectionRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "not found id" + id));
        ContentSectionMapper.INSTANCE.createNotLossData(contentSection,contentSectionUpdateDto);
        contentSectionRepository.save(contentSection);
    }

    @Override
    public void deleteContentSection(Long id) {
        findById(id);
        contentSectionRepository.deleteById(id);
    }
}
