package com.jomnam.videocourse.api.content.web;

import com.jomnam.videocourse.api.category.CategoryService;
import com.jomnam.videocourse.api.content.Content;
import com.jomnam.videocourse.api.content.ContentService;
import com.jomnam.videocourse.exception.ResourceExceptionHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
        ContentDto contentDto =contentService.findContentById(id);
        return ResponseEntity.ok(contentDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateContentById(@PathVariable("id") Long id,@RequestBody ContentUpdateDto contentUpdateDto){
        contentService.updateContentById(id,contentUpdateDto);
        return ResponseEntity.ok().build();
    }
    @GetMapping()
    public ResponseEntity<?> findContentAll(){
        List<ContentDto> list = contentService.findAll();
        return ResponseEntity.ok(list);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable("id") Long id){
        contentService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
