package com.example.YouTube.dto.video;

import com.example.YouTube.dto.attach.PreviewAttachDTO;
import com.example.YouTube.dto.channel.ChannelShortInfoDTO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VideoShortDTO {
    private String id;
    private String title;
    private ChannelShortInfoDTO channel;
    private Double duration;
    private PreviewAttachDTO previewAttach;
}
