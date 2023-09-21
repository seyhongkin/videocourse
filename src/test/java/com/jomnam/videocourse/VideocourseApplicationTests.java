package com.jomnam.videocourse;

import com.jomnam.videocourse.api.content.ContentService;
import com.jomnam.videocourse.api.content.web.ContentCreateDto;
import com.jomnam.videocourse.api.content.web.ContentDto;
import com.jomnam.videocourse.api.content.web.ContentUpdateDto;
import com.jomnam.videocourse.api.content_section.ContentSection;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.WebProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class VideocourseApplicationTests {

    @Autowired
    private ContentService contentService;

    @Test
    void testContentFindAll() {
        CollectionModel<?> contentDtoList = contentService.findAll();
        assertNotNull(contentDtoList);
        assertFalse(contentDtoList.getContent().isEmpty()); // Ensure the list is not empty
//        assertEquals(3, contentDtoList.size()); // Assuming you expect 3 items in the list
        System.out.println(contentDtoList);
    }

    @Test
    void testContentGetById() {
        // Provide a valid ID for an existing content item in your test database
        Long contentId = 1L; // Replace with a valid ID from your data

        EntityModel<?> contentDto = contentService.findContentById(contentId);
        assertNotNull(contentDto);
        // Add additional assertions to verify the correctness of the contentDto
        System.out.println(contentDto);
    }

    @Test
    void testContentUpdate() {
        contentService.updateContentById(1L, ContentUpdateDto.builder().duration(132F).title("Come in the game").link("hai@gmail.com").type("new jeans").build());
    }

    @Test
    @Transactional
    void testContentCreate() {
        // Create a ContentSection entity and save it to the database
        ContentSection contentSection = new ContentSection();
        contentSection.setTitle("Sample Section"); // Replace with the appropriate field values
//        contentSection = contentService.createContent(contentSection); // Ensure you have a method for saving content sections

        // Create a ContentCreateDto with valid data
        ContentCreateDto contentDto = ContentCreateDto.builder()
                .contentSection(contentSection)
                .duration(132F)
                .title("Come in the game")
                .link("https://example.com/game-link") // Provide a valid link
                .type("new jeans")
                .build();

        // Create the content using the ContentService
        contentService.createContent(contentDto);
    }

    @Test
    void testContentDelete() {
        contentService.deleteById(1L);
    }

}
