package com.example.signalbackend.domain.admin.presentation.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@NoArgsConstructor
@Getter
public class UpdateHospitalImageRequest {
    @NotBlank
    private String hospital_image;
}
