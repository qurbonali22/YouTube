package com.example.YouTube.dto.playlist;

import com.example.YouTube.dto.VideoDTO;
import com.example.YouTube.dto.channel.ChannelDTO;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class PlaylistShortInfoDTO {

    private String id;
    private String name;
    private ChannelDTO channel; //(id, name)
    private Integer video_count;
    private VideoDTO video_list; // [{id,name,duration}]
}
