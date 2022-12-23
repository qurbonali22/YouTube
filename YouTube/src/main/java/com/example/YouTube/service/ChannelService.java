package com.example.YouTube.service;

import com.example.YouTube.dto.channel.ChannelCreationDTO;
import com.example.YouTube.dto.channel.ChannelDTO;
import com.example.YouTube.dto.channel.ChannelUpdateAttachDTO;
import com.example.YouTube.dto.channel.ChannelUpdateDTO;
import com.example.YouTube.entity.ChannelEntity;
import com.example.YouTube.enums.GeneralStatus;
import com.example.YouTube.enums.Language;
import com.example.YouTube.exception.ItemNotFoundException;
import com.example.YouTube.repository.ChannelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
public class ChannelService {

    @Autowired
    ResourceBundleService resourceBundleService;

    @Autowired
    private ChannelRepository channelRepository;

    public ChannelDTO create(ChannelCreationDTO dto, Integer profileId){
        ChannelEntity entity = new ChannelEntity();
        entity.setName(dto.getName());
        entity.setDescription(dto.getDescription());
        entity.setProfileId(profileId);
        entity.setBannerId(dto.getBannerId());
        entity.setPhotoId(dto.getPhotoId());
        entity.setStatus(GeneralStatus.ACTIVE);
        entity.setCreatedDate(LocalDateTime.now());

        channelRepository.save(entity);

        return toDTO(entity);
    }

    public Boolean updateChannelPhoto(String id, ChannelUpdateAttachDTO dto, Language language){

        ChannelEntity entity = get(id, language);
        entity.setPhotoId(dto.getAttachId());
        channelRepository.save(entity);

        return true;
    }

    public Boolean updateChannelBanner(String id, ChannelUpdateAttachDTO dto, Language language){

        ChannelEntity entity = get(id, language);
        entity.setBannerId(dto.getAttachId());
        channelRepository.save(entity);

        return true;
    }

    public Boolean updateChannel(String id, ChannelUpdateDTO dto, Language language){

        ChannelEntity entity = get(id, language);
        entity.setName(dto.getName());
        entity.setDescription(dto.getDescription());

        channelRepository.save(entity);

        return true;
    }

    public ChannelDTO getById(String id, Language language){
        ChannelEntity entity = get(id, language);
        return toDTO(entity);
    }

    public Page<ChannelDTO> getPage(Integer page, Integer size){

        Pageable pageable = PageRequest.of(page, size);

        Page<ChannelEntity> obj = channelRepository.findAll(pageable);
        List<ChannelEntity> entityList = obj.getContent();

        List<ChannelDTO> dtoList = new LinkedList<>();

        for (ChannelEntity entity : entityList) {
            dtoList.add(toDTO(entity));
        }

        return new PageImpl<>(dtoList, pageable, obj.getTotalElements());
    }

    public Boolean changeChannelStatus(String id, Language language){

        ChannelEntity entity = get(id, language);
        entity.setStatus(entity.getStatus().equals(GeneralStatus.ACTIVE)?GeneralStatus.BLOCK:GeneralStatus.ACTIVE);
        return true;
    }

    public List<ChannelDTO> getChannelList(Integer profileId){

        List<ChannelEntity> entityList = channelRepository.findByProfileId(profileId);
        List<ChannelDTO> dtoList = new LinkedList<>();

        for (ChannelEntity entity : entityList) {
            dtoList.add(toDTO(entity));
        }

        return dtoList;
    }

    public ChannelEntity get(String id, Language language){

        Optional<ChannelEntity> optional = channelRepository.findById(id);
        return optional.orElseThrow(() -> {
            throw new ItemNotFoundException(resourceBundleService.getMessage("item.not.found", language, id));
        });
    }

    public ChannelDTO toDTO(ChannelEntity entity){
        ChannelDTO dto = new ChannelDTO();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setDescription(entity.getDescription());
        dto.setCreatedDate(entity.getCreatedDate());

        dto.setProfileId(entity.getProfileId());
        dto.setPhotoId(entity.getPhotoId());
        dto.setBannerId(entity.getBannerId());

        return dto;
    }


}
