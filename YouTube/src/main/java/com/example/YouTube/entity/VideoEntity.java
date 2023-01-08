package com.example.YouTube.entity;

import com.example.YouTube.enums.VideoStatus;
import com.example.YouTube.enums.VideoType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "video")
public class VideoEntity {
    @Id
    @GeneratedValue(generator = "generator_uuid")
    @GenericGenerator(name = "video_uuid", strategy = "org.hibernate.id.UUIDGenerator")
    private String id;
    @Column
    private String title;
    @Column
    @Enumerated(EnumType.STRING)
    private VideoStatus status;

    @Column(name = "shared_count")
    private long sharedCount;

    @Column
    private String description;

    @Column
    private long viewCount;

    @Column(name = "like_count")
    private long likeCount;

    @Column(name = "dislike_count")
    private long dislikeCount;

    @Column(name = "created_date")
    private LocalDateTime createdDate;

    @Column(name = "published_date")
    private LocalDateTime publishedDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "video_type")
    private VideoType type;

    @Column(name = "category_id",unique = true)
    private Integer categoryId;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", insertable = false, updatable = false,unique = true)
    private CategoryEntity category;

    @Column(name = "attach_id")
    private String attachId;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "attach_id", insertable = false, updatable = false)
    private AttachEntity attach;

    @Column(name = "preview_attach_id")
    private String previewAttachId;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "preview_attach_id", insertable = false, updatable = false)
    private AttachEntity previewAttach;

    @Column(name = "channel_id")
    private String channelId;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "channel_id", insertable = false, updatable = false)
    private ChannelEntity channel;

    @Column
    private Double duration;

    @Column(name = "owner_id")
    private Integer ownerId;
    @ManyToOne
    @JoinColumn(name = "owner_id", insertable = false, updatable = false)
    private ProfileEntity owner;


}


