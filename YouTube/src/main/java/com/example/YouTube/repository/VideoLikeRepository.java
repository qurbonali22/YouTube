package com.example.YouTube.repository;

import com.example.YouTube.entity.VideoLikeEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VideoLikeRepository extends CrudRepository<VideoLikeEntity, String> {

    Optional<VideoLikeEntity> findByProfileIdAndVideoId(String profile, String video);

}
