package com.example.YouTube.controller;

import com.example.YouTube.dto.tag.TagCreationDTO;
import com.example.YouTube.dto.tag.TagDTO;
import com.example.YouTube.enums.Language;
import com.example.YouTube.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tag")
public class TagController {

    @Autowired
    private TagService tagService;

    @PostMapping("/create")
    public ResponseEntity<TagDTO> save(@RequestBody TagCreationDTO dto){
        return ResponseEntity.ok(tagService.create(dto));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/update/{id}")
    public ResponseEntity<Boolean> update(@PathVariable("id") Integer id,
                                          @RequestBody TagCreationDTO dto,
                                          @RequestHeader(value = "Accept-Language", defaultValue = "UZ") Language language){

        return ResponseEntity.ok(tagService.update(id, dto, language));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable("id") Integer id,
                                          @RequestHeader(value = "Accept-Language", defaultValue = "UZ") Language language){

        return ResponseEntity.ok(tagService.delete(id, language));
    }

    @GetMapping("getList")
    public ResponseEntity<List<TagDTO>> getList(){
        return ResponseEntity.ok(tagService.getList());
    }
}
