package com.example.YouTube.repository;

import com.example.YouTube.entity.ReportEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface ReportRepository extends CrudRepository<ReportEntity, Integer>, PagingAndSortingRepository<ReportEntity, Integer> {

    List<ReportEntity> findByProfileId(Integer profile);

}
