package com.example.YouTube.controller;


import com.example.YouTube.dto.videoLike.VideoLakeCreationDTO;
import com.example.YouTube.dto.videoLike.VideoLikeDTO;
import com.example.YouTube.service.VideoLikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/videoLake")
public class VideoLikeController {
    @Autowired
    private VideoLikeService videoLikeService;

    @PostMapping("/create")
    public ResponseEntity<VideoLikeDTO> save(@RequestBody VideoLakeCreationDTO dto){
        return ResponseEntity.ok(videoLikeService.create(dto));
    }

    @DeleteMapping("/remove")
    public ResponseEntity<Boolean> remove(@RequestBody VideoLakeCreationDTO dto){

        return ResponseEntity.ok(videoLikeService.remove(dto));
    }
}
