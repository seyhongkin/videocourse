package com.jomnam.videocourse.api.content.web;


import com.jomnam.videocourse.api.content.ContentService;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/v1/contents")
@RequiredArgsConstructor
public class ContentController {
    private final ContentService contentService;

    @PostMapping
    public ResponseEntity<?> createContent(@RequestBody ContentCreateDto contentCreateDto){
        contentService.createContent(contentCreateDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable("id") Long id){
        EntityModel<?> contentDto =contentService.findContentById(id);
        return ResponseEntity.ok(contentDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateContentById(@PathVariable("id") Long id,@RequestBody ContentUpdateDto contentUpdateDto){
        contentService.updateContentById(id,contentUpdateDto);
        return ResponseEntity.ok().build();
    }
    @GetMapping()
    public ResponseEntity<?> findContentAll(){
        CollectionModel<?> list = contentService.findAll();
        return ResponseEntity.ok(list);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable("id") Long id){
        contentService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
