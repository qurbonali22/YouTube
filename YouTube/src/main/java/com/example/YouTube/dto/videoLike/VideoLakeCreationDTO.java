package com.example.YouTube.dto.videoLike;

import com.example.YouTube.enums.VideoLike;
import lombok.Data;

@Data
public class VideoLakeCreationDTO {
    private String profileId;
    private String videoId;
    private VideoLike type;
}
