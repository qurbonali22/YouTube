package com.example.YouTube.repository;

import com.example.YouTube.entity.ChannelEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ChannelRepository extends CrudRepository<ChannelEntity, Integer> {

}
