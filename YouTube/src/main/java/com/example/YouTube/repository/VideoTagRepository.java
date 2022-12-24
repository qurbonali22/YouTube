package com.example.YouTube.repository;

import com.example.YouTube.entity.VideoTagEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface VideoTagRepository extends CrudRepository<VideoTagEntity, String > {

    Optional<VideoTagEntity> findByVideoIdAndTagId(String video, Integer tag);

    List<VideoTagEntity> findByVideoId(String video);

}
