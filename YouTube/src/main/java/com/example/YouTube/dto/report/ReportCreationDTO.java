package com.example.YouTube.dto.report;

import com.example.YouTube.enums.ReportType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReportCreationDTO {

    private String entityId;
    private ReportType type;
    private String content;
}
