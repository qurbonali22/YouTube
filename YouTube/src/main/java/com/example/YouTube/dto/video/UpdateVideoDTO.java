package com.example.YouTube.dto.video;

import com.example.YouTube.enums.VideoStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateVideoDTO {
    private String id;
    private String title;
    private VideoStatus status;
    private String description;
    private Integer categoryId;
    private String previewAttachId;
}
