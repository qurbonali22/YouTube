package com.example.YouTube.service;

import com.example.YouTube.dto.tag.TagDTO;
import com.example.YouTube.dto.videoTag.VideoTagCreationDTO;
import com.example.YouTube.dto.videoTag.VideoTagDTO;
import com.example.YouTube.entity.TagEntity;
import com.example.YouTube.entity.VideoTagEntity;
import com.example.YouTube.enums.Language;
import com.example.YouTube.exception.ItemNotFoundException;
import com.example.YouTube.repository.VideoTagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
public class VideoTagService {

    @Autowired
    private VideoTagRepository videoTagRepository;

    @Autowired
    private TagService tagService;

    public VideoTagDTO create(VideoTagCreationDTO dto){

        VideoTagEntity entity = new VideoTagEntity();
        entity.setTagId(dto.getTagId());
        entity.setVideoId(dto.getVideoId());
        entity.setCreatedDate(LocalDateTime.now());

        videoTagRepository.save(entity);
        return toDTO(entity);
    }

    public Boolean delete(VideoTagCreationDTO dto, Language language){
        VideoTagEntity entity = getByVideoIdAndTagId(dto.getVideoId(), dto.getTagId(), language);
        videoTagRepository.delete(entity);
        return true;
    }

    public List<VideoTagDTO> getByVideoId(String id, Language language){
        List<VideoTagEntity> entityList = videoTagRepository.findByVideoId(id);
        List<VideoTagDTO> dtoList = new LinkedList<>();

        for (VideoTagEntity entity : entityList) {
            dtoList.add(toDTO2(entity, language));
        }

        return dtoList;
    }

    public VideoTagEntity getByVideoIdAndTagId(String video, Integer tag, Language language){

        Optional<VideoTagEntity> optional = videoTagRepository.findByVideoIdAndTagId(video, tag);

        return optional.orElseThrow(() -> {
            throw new ItemNotFoundException("Video id or Tag id incorrect");
        });
    }

    public VideoTagDTO toDTO(VideoTagEntity entity){
        VideoTagDTO dto = new VideoTagDTO();
        dto.setId(entity.getId());
        dto.setTagId(entity.getTagId());
        dto.setVideoId(entity.getVideoId());
        dto.setCreatedDate(entity.getCreatedDate());

        return dto;
    }

    public VideoTagDTO toDTO2(VideoTagEntity entity, Language language){
        VideoTagDTO dto = new VideoTagDTO();
        dto.setId(entity.getId());

        TagEntity tagEntity = tagService.get(entity.getTagId(), Language.EN);
        TagDTO tag = new TagDTO();
        tag.setId(tagEntity.getId());
        tag.setName(tagEntity.getName());
        dto.setTag(tag);

        dto.setVideoId(entity.getVideoId());
        dto.setCreatedDate(entity.getCreatedDate());

        return dto;
    }

}
