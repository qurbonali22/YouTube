package com.example.YouTube.dto.channel;

import com.example.YouTube.enums.ProfileStatus;
import jakarta.persistence.Column;
import lombok.Data;

@Data
public class ChannelDTO {

    private Integer id;

    private String name;

    private String photo;

    private String description;

    private ProfileStatus status;

    private String banner;

    private Integer profileId;

}
