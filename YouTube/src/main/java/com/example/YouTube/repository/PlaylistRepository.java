package com.example.YouTube.repository;

import com.example.YouTube.entity.PlaylistEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface PlaylistRepository extends CrudRepository<PlaylistEntity, String >,
                                            PagingAndSortingRepository<PlaylistEntity, String> {



}
