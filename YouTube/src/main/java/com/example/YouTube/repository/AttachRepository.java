package com.example.YouTube.repository;

import com.example.YouTube.entity.AttachEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface AttachRepository extends PagingAndSortingRepository<AttachEntity, String>,
                                            CrudRepository<AttachEntity, String> {


}
