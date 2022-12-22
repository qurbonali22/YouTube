package com.example.YouTube.service;

import com.example.YouTube.dto.channel.ChannelDTO;
import com.example.YouTube.entity.ChannelEntity;
import com.example.YouTube.entity.ProfileEntity;
import com.example.YouTube.enums.ProfileRole;
import com.example.YouTube.enums.ProfileStatus;
import com.example.YouTube.exception.ChannelNotFoundException;
import com.example.YouTube.exception.ProfileIdNotFound;
import com.example.YouTube.repository.ChannelRepository;
import com.example.YouTube.repository.ProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ChannelService {
    @Autowired
    private ChannelRepository channelRepository;

    @Autowired
    private ProfileRepository profileRepository;

    //    1. Create Channel (USER)
//    2. Update Channel ( USER and OWNER)
//    3. Update Channel photo ( USER and OWNER)
//    4. Update Channel banner ( USER and OWNER)
//    5. Channel Pagination (ADMIN)
//    6. Get Channel By Id
//    7. Change Channel Status (ADMIN,USER and OWNER)
//    8. User Channel List (murojat qilgan userni)
//        id(uuid),name,photo,description,status (ACTIVE, BLOCK),banner,profile_id

    public ChannelDTO create(ChannelDTO dto){
        Optional<ProfileEntity> byProfile = profileRepository.findById(dto.getProfileId());
        if (byProfile.isEmpty()) {
            throw new ProfileIdNotFound("This profile not found!!!");
        }
        ProfileEntity profile = byProfile.get();
        if(profile.getRole().equals(ProfileRole.ROLE_USER ) ||
                profile.getRole().equals(ProfileRole.ROLE_OWNER)){
            ChannelEntity entity = toEntity(dto);
            channelRepository.save(entity);
            return dto;
        }
        return null;
    }
    public Boolean update(Integer id, ChannelDTO dto){
        Optional<ChannelEntity> byIdChannel = channelRepository.findById(id);
        if(byIdChannel.isEmpty()){
            throw new ProfileIdNotFound("This id not found!!!");
        }
        Optional<ProfileEntity> byProfile = profileRepository.findById(dto.getProfileId());
        if (byProfile.isEmpty()) {
            throw new ProfileIdNotFound("This profile not found!!!");
        }
        ProfileEntity profile = byProfile.get();
        if(profile.getRole().equals(ProfileRole.ROLE_USER ) ||
                profile.getRole().equals(ProfileRole.ROLE_OWNER)){
            ChannelEntity entity = byIdChannel.get();
            entity = toEntity(dto);
            entity.setId(id);
            channelRepository.save(entity);
            return true;
        }
        return false;
    }
    public Boolean updatePhoto(Integer id, String photo){
        Optional<ChannelEntity> byIdChannel = channelRepository.findById(id);
        if(byIdChannel.isEmpty()){
            throw new ProfileIdNotFound("This id not found!!!");
        }ChannelEntity channel = byIdChannel.get();
        Optional<ProfileEntity> byProfile = profileRepository.findById(channel.getProfileId());
        if (byProfile.isEmpty()) {
            throw new ProfileIdNotFound("This profile not found!!!");
        }
        ProfileEntity profile = byProfile.get();
        if(profile.getRole().equals(ProfileRole.ROLE_USER ) ||
                profile.getRole().equals(ProfileRole.ROLE_OWNER)){
            ChannelEntity entity = byIdChannel.get();
            entity.setPhoto(photo);
            channelRepository.save(entity);
            return true;
        }
        return false;
    }
    public Boolean updateBanner(Integer id, String banner){
        Optional<ChannelEntity> byIdChannel = channelRepository.findById(id);
        if(byIdChannel.isEmpty()){
            throw new ProfileIdNotFound("This id not found!!!");
        } ChannelEntity channel = byIdChannel.get();
        Optional<ProfileEntity> byProfile = profileRepository.findById(channel.getProfileId());
        if (byProfile.isEmpty()) {
            throw new ProfileIdNotFound("This profile not found!!!");
        }
        ProfileEntity profile = byProfile.get();
        if(profile.getRole().equals(ProfileRole.ROLE_USER ) ||
                profile.getRole().equals(ProfileRole.ROLE_OWNER)){
            ChannelEntity entity = byIdChannel.get();
            entity.setBanner(banner);
            channelRepository.save(entity);
            return true;
        }
        return false;
    }
    public ChannelDTO getChannelById(Integer id){
        Optional<ChannelEntity> optional = channelRepository.findById(id);
        if(optional.isEmpty()){
            throw new ChannelNotFoundException("This id not found!!!");
        }ChannelDTO dto = toDTO(optional.get());
        return dto;
    }
    public Boolean changeStatus(Integer id, ProfileStatus status){
        Optional<ChannelEntity> byIdChannel = channelRepository.findById(id);
        if(byIdChannel.isEmpty()){
            throw new ProfileIdNotFound("This id not found!!!");
        }ChannelEntity channel = byIdChannel.get();
        Optional<ProfileEntity> byProfile = profileRepository.findById(channel.getProfileId());
        if (byProfile.isEmpty()) {
            throw new ProfileIdNotFound("This profile not found!!!");
        }
        ProfileEntity profile = byProfile.get();
        if(profile.getRole() != null){
            ChannelEntity entity = byIdChannel.get();
            entity.setStatus(status);
            channelRepository.save(entity);
            return true;
        }
        return false;
    }
    public List<ChannelDTO> getUserChanel(Integer id){
        Iterable<ChannelEntity> listbyProfileId = channelRepository.findByProfileId(id);
        List<ChannelDTO> channelDtoList = new ArrayList<>();
        for(ChannelEntity entity:listbyProfileId){
            channelDtoList.add(toDTO(entity));
        }return channelDtoList;

    }

    public ChannelDTO toDTO(ChannelEntity entity) {
        ChannelDTO dto = new ChannelDTO();
        dto.setName(entity.getName());
        dto.setPhoto(entity.getPhoto());
        dto.setDescription(entity.getDescription());
        dto.setBanner(entity.getBanner());
        dto.setStatus(entity.getStatus());
        dto.setProfileId(entity.getProfileId());
        return dto;
    }
    public ChannelEntity toEntity(ChannelDTO dto){
        ChannelEntity entity = new ChannelEntity();
        entity.setName(dto.getName());
        entity.setPhoto(dto.getPhoto());
        entity.setDescription(dto.getDescription());
        entity.setBanner(dto.getBanner());
        entity.setStatus(dto.getStatus());
        entity.setProfileId(dto.getProfileId());
        return entity;
    }
}
