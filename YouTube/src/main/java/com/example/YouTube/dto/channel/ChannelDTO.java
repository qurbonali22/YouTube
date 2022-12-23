package com.example.YouTube.dto.channel;

import com.example.YouTube.entity.AttachEntity;
import com.example.YouTube.entity.ProfileEntity;
import com.example.YouTube.enums.GeneralStatus;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Setter
@Getter
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ChannelDTO {

    private String id;
    private String name;
    private String photoId;
    private String description;
    private GeneralStatus status;
    private String bannerId;
    private Integer profileId;
    private LocalDateTime createdDate;

    private AttachEntity photo;
    private AttachEntity banner;
    private ProfileEntity profile;
}
