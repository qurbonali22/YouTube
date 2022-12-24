package com.example.YouTube.dto.video;

import com.example.YouTube.dto.attach.AttachResponseDTO;
import com.example.YouTube.dto.category.CategoryDTO;
import com.example.YouTube.dto.channel.ChannelDTO;
import com.example.YouTube.enums.VideoStatus;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class VideoDTO {
    private String id;
    private String title;
    private VideoStatus status;
    private long sharedCount;
    private String description;
    private long viewCount;
    private long likeCount;
    private long dislikeCount;
    private LocalDateTime createdDate;

    private Integer categoryId;
    private CategoryDTO category;

    private String attachId;
    private AttachResponseDTO attach;

    private String previewAttachId;
    private AttachResponseDTO previewAttach;

    private String channelId;
    private ChannelDTO channel;

}

