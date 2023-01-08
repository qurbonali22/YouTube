package com.example.YouTube.dto.playlist;

import com.example.YouTube.dto.channel.ChannelShortInfoDTO;
import com.example.YouTube.dto.video.VideoSmallInfoDTO;

import com.example.YouTube.enums.PlaylistStatus;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class PlayListShortInfoDTO {
    private Integer id;
    private ChannelShortInfoDTO channel;
    private Integer videoCount;
    private List<VideoSmallInfoDTO> videoList;
    private PlaylistStatus status;
    private Integer orderNum;
}
