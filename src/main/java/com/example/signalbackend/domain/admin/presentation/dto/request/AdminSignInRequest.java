package com.example.signalbackend.domain.admin.presentation.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Getter
@NoArgsConstructor
public class AdminSignInRequest {

    @NotBlank
    private String accountId;

    @NotBlank
    private String password;
}
