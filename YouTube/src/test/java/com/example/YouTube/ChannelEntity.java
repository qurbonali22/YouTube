package com.example.YouTube.entity;

import com.example.YouTube.enums.ProfileStatus;
import jakarta.persistence.*;
import lombok.Data;
//id(uuid),name,photo,description,status (ACTIVE, BLOCK),banner,profile_id
@Data
@Entity
@Table(name = "channel")
public class ChannelEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String name;

    @Column
    private String photo;

    @Column
    private String description;

    @Column
    private ProfileStatus status;

    @Column
    private String banner;

    @Column(name = "profile_id")
    private Integer profileId;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "profile_id", insertable = false, updatable = false)
    private ProfileEntity profile;
}
