package com.jomnam.videocourse.api.content_section.web;

import com.jomnam.videocourse.api.content_section.ContentSectionService;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/content-sections")
public class ContentSectionController {
    private final ContentSectionService contentSectionService;
    @PostMapping
    public ResponseEntity<?> createSection(@RequestBody ContentSectionCreate contentSectionCreate){
        contentSectionService.createContentSection(contentSectionCreate);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
    @GetMapping
    public CollectionModel<?> findAll(){
        CollectionModel<?> fCollectionModel = contentSectionService.findAll();
        return fCollectionModel;
    }
    @GetMapping("/{id}")
    public CollectionModel<?> getById(@PathVariable("id") Long id){
        return null;
    }
}
