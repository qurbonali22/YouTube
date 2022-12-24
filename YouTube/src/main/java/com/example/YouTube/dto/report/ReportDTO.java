package com.example.YouTube.dto.report;

import com.example.YouTube.dto.profile.ProfileDTO;
import com.example.YouTube.enums.ReportType;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ReportDTO {

    private Integer id;
    private ProfileDTO profile;
    private String content;
    private String entityId;
    private ReportType type;
    private LocalDateTime createdDate;
}
