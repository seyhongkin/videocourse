package com.jomnam.videocourse.api.content_section.web;

import com.jomnam.videocourse.api.content_section.ContentSectionService;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
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
        return contentSectionService.findAll();
    }
    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<?>> getById(@PathVariable("id") Long id){
        return ResponseEntity.ok().body(contentSectionService.findById(id));
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> updateContentById(@PathVariable("id" )Long id,@RequestBody ContentSectionUpdateDto contentSectionUpdateDto){
       contentSectionService.updateContentSection(id,contentSectionUpdateDto);
        return ResponseEntity.ok().build();
    }
}
