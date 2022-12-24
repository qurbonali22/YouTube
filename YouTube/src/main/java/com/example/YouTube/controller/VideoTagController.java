package com.example.YouTube.controller;

import com.example.YouTube.dto.videoTag.VideoTagCreationDTO;
import com.example.YouTube.dto.videoTag.VideoTagDTO;
import com.example.YouTube.enums.Language;
import com.example.YouTube.service.VideoTagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/videoTag")
public class VideoTagController {

    @Autowired
    private VideoTagService videoTagService;

    @PreAuthorize("hasAnyRole('USER', 'OWNER')")
    @PostMapping("/create")
    public ResponseEntity<VideoTagDTO> save(@RequestBody VideoTagCreationDTO dto){
        return ResponseEntity.ok(videoTagService.create(dto));
    }

    @PreAuthorize("hasAnyRole('USER', 'OWNER')")
    @DeleteMapping("/delete")
    public ResponseEntity<Boolean> delete(@RequestBody VideoTagCreationDTO dto,
                                          @RequestHeader(value = "Accept-Language", defaultValue = "UZ") Language language){

        return ResponseEntity.ok(videoTagService.delete(dto, language));
    }

    @GetMapping("/public/getList/{id}")
    public ResponseEntity<List<VideoTagDTO>> getList(@PathVariable("id") String id,
                                       @RequestHeader(value = "Accept-Language", defaultValue = "UZ") Language language){

        return ResponseEntity.ok(videoTagService.getByVideoId(id, language));
    }

}
