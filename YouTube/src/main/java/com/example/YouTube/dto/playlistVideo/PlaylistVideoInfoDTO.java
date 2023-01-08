package com.example.YouTube.dto.playlistVideo;

import com.example.YouTube.dto.video.VideoShortDTO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PlaylistVideoInfoDTO {
    private Integer playlistId;
    private VideoShortDTO video;

}
