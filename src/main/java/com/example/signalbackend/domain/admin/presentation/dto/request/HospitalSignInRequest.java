package com.example.signalbackend.domain.admin.presentation.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@NoArgsConstructor
@Getter
public class HospitalSignInRequest {

    @NotBlank
    private String accountId;

    @NotBlank
    private String password;
}
