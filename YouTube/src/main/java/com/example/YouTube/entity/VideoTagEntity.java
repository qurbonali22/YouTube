package com.example.YouTube.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "video_tag")
public class VideoTagEntity {

    @Id
    @GeneratedValue(generator = "generator_uuid")
    @GenericGenerator(name = "generator_uuid", strategy = "org.hibernate.id.UUIDGenerator")
    private String id;

    @Column(name = "video_id")
    private String videoId;
    @ManyToOne
    @JoinColumn(name = "video_id", insertable = false, updatable = false)
    private VideoEntity video;

    @Column(name = "tag_id")
    private Integer tagId;
    @ManyToOne
    @JoinColumn(name = "tag_id", insertable = false, updatable = false)
    private TagEntity tag;

    @Column(name = "created_date")
    private LocalDateTime createdDate;
}
