package com.example.YouTube.repository;

import com.example.YouTube.entity.ProfileEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProfileRepository extends PagingAndSortingRepository<ProfileEntity, Integer>, CrudRepository<ProfileEntity,Integer> {
    Optional<ProfileEntity> findByEmail(String email);

    Optional<ProfileEntity> findByEmailAndPassword(String email, String md5);
}
