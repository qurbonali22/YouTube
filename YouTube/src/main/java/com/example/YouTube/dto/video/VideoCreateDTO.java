package com.example.YouTube.dto.video;

import com.example.YouTube.enums.VideoStatus;
import com.example.YouTube.enums.VideoType;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class VideoCreateDTO {
    private String id;
    private String title;
    private VideoStatus status;
    private String description;
    private VideoType type;
    private Integer categoryId;
    private String attachId;
    private String previewAttachId;
    private String channelId;
}
