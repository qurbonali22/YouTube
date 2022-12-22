package com.example.YouTube.repository;

import com.example.YouTube.entity.ProfileEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfileRepository extends PagingAndSortingRepository<ProfileEntity, Integer>, CrudRepository<ProfileEntity,Integer> {
}
