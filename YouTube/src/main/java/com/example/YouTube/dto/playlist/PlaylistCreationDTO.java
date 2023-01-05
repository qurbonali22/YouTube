package com.example.YouTube.dto.playlist;

import com.example.YouTube.enums.PlaylistStatus;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class PlaylistCreationDTO {

    private String channel_id;
    private String name;
    private String description;
    private PlaylistStatus status;
    private Integer orderNum;
}
