package com.example.YouTube.controller;

import com.example.YouTube.dto.video.UpdateVideoDTO;
import com.example.YouTube.dto.video.VideoCreateDTO;
import com.example.YouTube.enums.Language;
import com.example.YouTube.service.VideoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/video")
@Tag(name = "AttachController API", description = "Api list for Attach")
public class VideoController {

    @Autowired
    private VideoService service;

    @PostMapping("/create")
    @PreAuthorize("hasRole('USER')")
    @Operation(summary = "video create", description = "Method used for creating of video")
    public ResponseEntity<?> create(@RequestBody VideoCreateDTO dto,
                                    @RequestHeader(value = "Accept-Language", defaultValue = "RU") Language language){
        return ResponseEntity.ok(service.create(dto,language));
    }

    @PutMapping("/updateVideo")
    @PreAuthorize("hasAnyRole('USER','OWNER')")
    @Operation(summary = " update video", description = "Method used for updating of video")
    public ResponseEntity<?> updateVideo(@RequestBody UpdateVideoDTO dto,
                                         @RequestHeader(value = "Accept-Language", defaultValue = "RU") Language language){
        return ResponseEntity.ok(service.updateVideo(dto, language));
    }


}
