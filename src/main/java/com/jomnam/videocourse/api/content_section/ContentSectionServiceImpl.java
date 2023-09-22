package com.jomnam.videocourse.api.content_section;

import com.jomnam.videocourse.api.content.web.ContentCreateDto;
import com.jomnam.videocourse.api.content_section.web.ContentSectionCreate;
import com.jomnam.videocourse.api.content_section.web.ContentSectionDto;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.CollectionModel;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ContentSectionServiceImpl implements ContentSectionService{
    private final ContentSectionModelAssembler contentSectionModelAssembler;
    private final ContentSectionRepository contentSectionRepository;
    @Override
    public CollectionModel<ContentSectionDto> findAll() {
        List<ContentSection> contentSections = contentSectionRepository.findAll();
       CollectionModel collectionModel = contentSectionModelAssembler.toCollectionModel(contentSections);
        return CollectionModel.of(collectionModel);
    }

    @Override
    public void createContentSection(ContentSectionCreate contentSectionCreate) {
        ContentSection contentSection = ContentSectionMapper.INSTANCE.dtoCreateToEntity(contentSectionCreate);

        contentSectionRepository.save(contentSection);
    }
}
