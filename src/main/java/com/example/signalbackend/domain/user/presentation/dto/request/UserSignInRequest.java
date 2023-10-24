package com.example.signalbackend.domain.user.presentation.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Getter
@NoArgsConstructor
public class UserSignInRequest {
    @NotBlank
    private String accountId;

    @NotBlank
    private String password;

    @NotBlank
    private String role;
}
