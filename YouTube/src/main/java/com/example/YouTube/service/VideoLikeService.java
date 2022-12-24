package com.example.YouTube.service;

import com.example.YouTube.dto.videoLike.VideoLakeCreationDTO;
import com.example.YouTube.dto.videoLike.VideoLikeDTO;
import com.example.YouTube.entity.VideoLikeEntity;
import com.example.YouTube.exception.ItemNotFoundException;
import com.example.YouTube.repository.VideoLikeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class VideoLikeService {
    @Autowired
    private VideoLikeRepository videoLikeRepository;

    public VideoLikeDTO create(VideoLakeCreationDTO dto){
        VideoLikeEntity entity = new VideoLikeEntity();
        entity.setProfileId(dto.getProfileId());
        entity.setVideoId(dto.getVideoId());
        entity.setCreatedDate(LocalDateTime.now());
        entity.setType(dto.getType());

        videoLikeRepository.save(entity);
        return toDTO(entity);
    }

    public Boolean remove(VideoLakeCreationDTO dto){
        VideoLikeEntity entity = getByProfileIdAndVideoId(dto.getProfileId(), dto.getVideoId());
        videoLikeRepository.delete(entity);
        return true;
    }

    public VideoLikeEntity getByProfileIdAndVideoId(String profile, String video){

        Optional<VideoLikeEntity> optional = videoLikeRepository.findByProfileIdAndVideoId(profile, video);

        return optional.orElseThrow(() -> {
            throw new ItemNotFoundException("profile id or video id incorrect");
        });
    }

    public VideoLikeDTO toDTO(VideoLikeEntity entity){
        VideoLikeDTO dto = new VideoLikeDTO();
        dto.setProfileId(entity.getProfileId());
        dto.setVideoId(entity.getVideoId());
        dto.setCreatedDate(entity.getCreatedDate());
        dto.setType(entity.getType());
        return dto;
    }

//    3. User Liked Comment List (order by created_date desc) (USER)
//    4. Get User LikedComment List By UserId (ADMIN)

//  ####################  ikkalasini bugun kechgacha bajarib qo'yaman #####################

}
