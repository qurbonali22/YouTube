package com.example.YouTube.dto.playlist;

import com.example.YouTube.dto.channel.ChannelShortDTO;
import com.example.YouTube.dto.profile.ProfileInfoDTO;
import com.example.YouTube.enums.PlaylistStatus;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PlayListInfoDTO {
    private Integer id;
    private String name;
    private String description;
    private PlaylistStatus status;
    private Integer orderNum;
    private ChannelShortDTO channel;
    private ProfileInfoDTO profile;

}