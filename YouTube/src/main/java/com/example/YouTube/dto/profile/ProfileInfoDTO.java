package com.example.YouTube.dto.profile;

import com.example.YouTube.dto.attach.PreviewAttachDTO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProfileInfoDTO {
    private Integer id;
    private String name;
    private String surname;
    private PreviewAttachDTO photo;

}
