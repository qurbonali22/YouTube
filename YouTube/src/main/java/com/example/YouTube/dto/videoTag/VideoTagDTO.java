package com.example.YouTube.dto.videoTag;

import com.example.YouTube.dto.tag.TagDTO;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class VideoTagDTO {

    private String id;
    private String videoId;
    private Integer tagId;
    private LocalDateTime createdDate;

    private TagDTO tag;
}
