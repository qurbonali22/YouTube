package com.example.YouTube.repository;

import com.example.YouTube.entity.PlaylistVideoEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;
import java.util.Optional;

public interface PlaylistVideoRepository extends CrudRepository<PlaylistVideoEntity, Integer>, PagingAndSortingRepository<PlaylistVideoEntity, Integer> {
    List<PlaylistVideoEntity> findByPlaylistId(Integer id);

    Optional<PlaylistVideoEntity> findByVideoIdAndPlaylistId(String videoId, Integer playlistId);
}
