package com.example.YouTube.entity;

import com.example.YouTube.enums.ReportType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "report")
public class ReportEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(columnDefinition = "text")
    private String content;

    @Column(name = "profile_id")
    private Integer profileId;
    @ManyToOne
    @JoinColumn(name = "profile_id", insertable = false, updatable = false)
    private ProfileEntity profile;

    @Column(name = "channel_id", nullable = true)
    private String channelId;
    @ManyToOne
    @JoinColumn(name = "channel_id", insertable = false, updatable = false, nullable = true)
    private ChannelEntity channel;

    @Column(name = "video_id")
    private String videoId;
    @ManyToOne
    @JoinColumn(name = "video_id", insertable = false, updatable = false)
    private VideoEntity video;

    @Enumerated(EnumType.STRING)
    @Column
    private ReportType type;

    @Column(name = "created_date")
    private LocalDateTime createdDate;
}
