package com.example.YouTube.repository;

import com.example.YouTube.entity.ChannelEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface ChannelRepository extends CrudRepository<ChannelEntity, String>,
                                 PagingAndSortingRepository<ChannelEntity, String> {

    List<ChannelEntity> findByProfileId(Integer id);
}
