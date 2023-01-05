package com.example.YouTube.service;

import com.example.YouTube.dto.playlist.PlaylistCreationDTO;
import com.example.YouTube.dto.playlist.PlaylistDTO;
import com.example.YouTube.dto.playlist.PlaylistUpdateDTO;
import com.example.YouTube.entity.PlaylistEntity;
import com.example.YouTube.enums.PlaylistStatus;
import com.example.YouTube.exception.ItemNotFoundException;
import com.example.YouTube.repository.PlaylistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PlaylistService {

    @Autowired
    private PlaylistRepository playlistRepository;

    public PlaylistDTO create(PlaylistCreationDTO dto){
        PlaylistEntity entity = new PlaylistEntity();
        entity.setName(dto.getName());
        entity.setOrderNum(dto.getOrderNum());
        entity.setStatus(dto.getStatus());
        entity.setChannelId(dto.getChannel_id());
        entity.setDescription(dto.getDescription());

        playlistRepository.save(entity);
        return toDTO(entity);
    }

    public Boolean updateStatus(String id, PlaylistStatus status){

        PlaylistEntity entity = get(id);
        entity.setStatus(status);
        playlistRepository.save(entity);
        return true;
    }

    public Boolean updateDetail(String id, PlaylistUpdateDTO dto){
        PlaylistEntity entity = get(id);
        entity.setName(dto.getName());
        entity.setDescription(dto.getDescription());
        entity.setOrderNum(dto.getOrderNum());

        playlistRepository.save(entity);
        return true;
    }

    public Boolean delete(String id){
        PlaylistEntity entity = get(id);
        playlistRepository.delete(entity);
        return true;
    }



    public PlaylistEntity get(String id){

        return playlistRepository.findById(id).orElseThrow(() -> {
            throw new ItemNotFoundException("Playlist not found");
        });
    }




    public PlaylistDTO toDTO(PlaylistEntity entity){
        PlaylistDTO dto = new PlaylistDTO();
        dto.setDescription(entity.getDescription());
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setStatus(entity.getStatus());
        dto.setOrderNum(entity.getOrderNum());
        dto.setChannel_id(entity.getChannelId());

        return dto;
    }



}
