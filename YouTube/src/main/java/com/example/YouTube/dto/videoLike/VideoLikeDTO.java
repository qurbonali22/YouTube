package com.example.YouTube.dto.videoLike;

import com.example.YouTube.dto.tag.TagDTO;
import com.example.YouTube.enums.VideoLike;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class VideoLikeDTO {
    private String id;
    private String profileId;
    private String videoId;
    private LocalDateTime createdDate;
    private VideoLike type;
}
