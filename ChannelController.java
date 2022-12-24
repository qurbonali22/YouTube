package com.example.YouTube.controller;

import com.example.YouTube.dto.category.CategoryCreationDTO;
import com.example.YouTube.dto.category.CategoryDTO;
import com.example.YouTube.dto.channel.ChannelCreationDTO;
import com.example.YouTube.dto.channel.ChannelDTO;
import com.example.YouTube.dto.channel.ChannelUpdateAttachDTO;
import com.example.YouTube.dto.channel.ChannelUpdateDTO;
import com.example.YouTube.entity.ChannelEntity;
import com.example.YouTube.enums.Language;
import com.example.YouTube.service.ChannelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/channel")
public class ChannelController {
    @Autowired
    private ChannelService channelService;

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/create")
    public ResponseEntity<ChannelDTO> save(@RequestBody ChannelCreationDTO dto){
        return ResponseEntity.ok(channelService.create(dto));
    }

    @PreAuthorize("hasAnyRole('USER','OWNER')")
    @PutMapping("/update/{profileId}")
    public ResponseEntity<Boolean> updatePhoto(@PathVariable("profileId") String id,
                                          @RequestBody ChannelUpdateAttachDTO dto,
                                          @RequestHeader(value = "Accept-Language", defaultValue = "UZ") Language language){

        return ResponseEntity.ok(channelService.updateChannelPhoto(id, dto, language));
    }

    @PreAuthorize("hasAnyRole('USER','OWNER')")
    @PutMapping("/update/{bannerId}")
    public ResponseEntity<Boolean> updateBanner(@PathVariable("bannerId") String id,
                                               @RequestBody ChannelUpdateAttachDTO dto,
                                               @RequestHeader(value = "Accept-Language", defaultValue = "UZ") Language language){

        return ResponseEntity.ok(channelService.updateChannelBanner(id, dto, language));
    }

    @PreAuthorize("hasAnyRole('USER','OWNER')")
    @PutMapping("/update/{id}")
    public ResponseEntity<Boolean> updateChannel(@PathVariable("id") String id,
                                                @RequestBody ChannelUpdateDTO dto,
                                                @RequestHeader(value = "Accept-Language", defaultValue = "UZ") Language language){

        return ResponseEntity.ok(channelService.updateChannel(id, dto, language));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/get")
    public ResponseEntity<?> getPage(@RequestParam("page") Integer page, @RequestParam("size") Integer size) {
        Page<ChannelDTO> result = channelService.getPage(page, size);
        return ResponseEntity.ok(result);
    }

    @PutMapping("/getchannel/{id}")
    public ResponseEntity<ChannelDTO> getById(@PathVariable("id") String id,
                                                @RequestHeader(value = "Accept-Language", defaultValue = "UZ") Language language){

        return ResponseEntity.ok(channelService.getById(id, language));
    }

    @PreAuthorize("hasAnyRole('ADMIN','USER','OWNER')")
    @PutMapping("/changestatus/{id}")
    public ResponseEntity<Boolean> changeStatus(@PathVariable("id") String id,
                                              @RequestHeader(value = "Accept-Language", defaultValue = "UZ") Language language){

        return ResponseEntity.ok(channelService.changeChannelStatus(id, language));
    }

    @GetMapping("/getList/{id}")
    public ResponseEntity<List<ChannelDTO>> getList(@PathVariable("id") Integer id){
        return ResponseEntity.ok(channelService.getChannelList(id));
    }

}
