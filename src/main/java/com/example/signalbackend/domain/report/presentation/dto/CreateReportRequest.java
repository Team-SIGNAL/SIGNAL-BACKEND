package com.example.signalbackend.domain.report.presentation.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Getter
@NoArgsConstructor
public class CreateReportRequest {
    private String image;

    @NotBlank
    private String content;
}
