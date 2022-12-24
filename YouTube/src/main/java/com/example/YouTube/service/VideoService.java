package com.example.YouTube.service;

import com.example.YouTube.dto.attach.AttachResponseDTO;
import com.example.YouTube.dto.category.CategoryDTO;
import com.example.YouTube.dto.channel.ChannelDTO;
import com.example.YouTube.dto.video.UpdateVideoDTO;
import com.example.YouTube.dto.video.VideoCreateDTO;
import com.example.YouTube.dto.video.VideoDTO;
import com.example.YouTube.entity.AttachEntity;
import com.example.YouTube.entity.CategoryEntity;
import com.example.YouTube.entity.ChannelEntity;
import com.example.YouTube.entity.VideoEntity;
import com.example.YouTube.enums.Language;
import com.example.YouTube.exception.VideoNotFoundException;
import com.example.YouTube.repository.VideoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class VideoService {

    private VideoRepository videoRepository;
    private ChannelService channelService;
    private CategoryService categoryService;
    private AttachService attachService;

    private ResourceBundleService resourceBundleService;
    @Autowired

    public VideoService(VideoRepository videoRepository,
                        ChannelService channelService,
                        CategoryService categoryService,
                        AttachService attachService,
                        ResourceBundleService resourceBundleService) {
        this.videoRepository = videoRepository;
        this.channelService = channelService;
        this.categoryService = categoryService;
        this.attachService = attachService;
        this.resourceBundleService = resourceBundleService;
    }

    public VideoDTO create(VideoCreateDTO dto, Language language) {
        VideoEntity entity=new VideoEntity();
        entity.setTitle(dto.getTitle());
        entity.setAttachId(dto.getAttachId());
        entity.setChannelId(dto.getChannelId());
        entity.setCategoryId(dto.getCategoryId());
        entity.setDescription(dto.getDescription());
        entity.setPreviewAttachId(dto.getPreviewAttachId());
        entity.setStatus(dto.getStatus());
        entity.setType(dto.getType());
        entity.setSharedCount(0);
        entity.setLikeCount(0);
        entity.setDislikeCount(0);
        entity.setViewCount(0);
        entity.setCreatedDate(LocalDateTime.now());
        videoRepository.save(entity);
        VideoDTO result=toDTO(entity,language);
        return result;
    }

    public VideoDTO updateVideo(UpdateVideoDTO dto, Language language) {
        VideoEntity entity=getVideoEntity(dto.getId(),language);
        entity.setTitle(dto.getTitle());
        entity.setDescription(dto.getDescription());
        entity.setCategoryId(dto.getCategoryId());
        entity.setPreviewAttachId(dto.getPreviewAttachId());
        entity.setStatus(dto.getStatus());
        videoRepository.save(entity);
        VideoDTO result=toDTO(entity,language);
        return result;
    }

    private VideoDTO toDTO(VideoEntity entity,Language language){
        VideoDTO dto=new VideoDTO();
        dto.setId(entity.getId());
        dto.setDescription(entity.getDescription());
        dto.setTitle(entity.getTitle());
        dto.setStatus(entity.getStatus());
        dto.setViewCount(entity.getViewCount());
        dto.setSharedCount(entity.getSharedCount());
        dto.setLikeCount(entity.getLikeCount());
        dto.setDislikeCount(entity.getDislikeCount());
        dto.setAttach(getAttach(entity.getAttachId(), language));
        dto.setChannel(getChannel(entity.getChannelId(), language));
        dto.setCategory(getCategory(entity.getCategoryId(), language));
        dto.setPreviewAttach(getAttach(entity.getPreviewAttachId(), language));
        dto.setCreatedDate(entity.getCreatedDate());
        return dto;
    }

    private VideoEntity getVideoEntity(String videoId, Language language){
        Optional<VideoEntity> optional=videoRepository.findById(videoId);
        if (optional.isEmpty()){
            throw new VideoNotFoundException(resourceBundleService.getMessage("video.not.found",language,videoId));
        }
        return optional.get();
    }
    private AttachResponseDTO getAttach(String attachId,Language language){
        AttachEntity entity=attachService.getAttach(attachId,language);

        AttachResponseDTO dto=new AttachResponseDTO();
        dto.setId(entity.getId());
        dto.setSize(entity.getSize());
        dto.setOriginalName(entity.getOriginalName());
        dto.setPath(entity.getPath());
        dto.setDuration(entity.getDuration());
        dto.setExtension(entity.getExtension());
        dto.setCreatedData(entity.getCreatedDate());
        return dto;
    }

    private ChannelDTO getChannel(String channelId, Language language){
        ChannelEntity entity= channelService.get(channelId,language);

        ChannelDTO dto=new ChannelDTO();
        dto.setId(entity.getId());
        dto.setDescription(entity.getDescription());
        dto.setStatus(entity.getStatus());
        dto.setName(entity.getName());
        dto.setCreatedDate(entity.getCreatedDate());
//   todo channelni o`zgartirish kerak     dto.setProfile();
//        dto.setPhoto();
//        dto.setBanner();
        return dto;
    }

    private CategoryDTO getCategory(Integer categoryId,Language language ){
        CategoryEntity entity= categoryService.get(categoryId,language);

        CategoryDTO dto=new CategoryDTO();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setCreatedDate(entity.getCreatedDate());
        return dto;
    }


}
