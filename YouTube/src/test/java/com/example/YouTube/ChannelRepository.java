package com.example.YouTube.repository;

import com.example.YouTube.entity.ChannelEntity;
import com.example.YouTube.entity.ProfileEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ChannelRepository extends PagingAndSortingRepository<ChannelEntity, Integer>, CrudRepository<ChannelEntity, Integer> {
    @Override
    Iterable<ChannelEntity> findAllById(Iterable<Integer> integers);

    Iterable<ChannelEntity> findByProfileId(Integer integer);
}
