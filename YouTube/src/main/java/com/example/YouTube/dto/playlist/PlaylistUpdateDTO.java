package com.example.YouTube.dto.playlist;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class PlaylistUpdateDTO {

    private String name;
    private String description;
    private Integer orderNum;
}
