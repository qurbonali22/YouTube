package com.example.YouTube.dto.playlist;

import com.example.YouTube.dto.channel.ChannelDTO;
import com.example.YouTube.dto.profile.ProfileDTO;
import com.example.YouTube.enums.PlaylistStatus;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class PlaylistInfoDTO {

    private String id;
    private String name;
    private String description;
    private PlaylistStatus status;
    private Integer orderNum;
    private ChannelDTO channel; //(id, name, photo(id,url))
    private ProfileDTO video_list; // {id, name, surname, photo(id,url) }
}
