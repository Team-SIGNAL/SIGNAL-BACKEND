package com.example.signalbackend.domain.user.presentation.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Getter
@NoArgsConstructor
public class UpdateUserProfileRequest {
    @NotBlank
    private String image;
}
