package com.example.signalbackend.domain.admin.presentation.dto.response;

import com.example.signalbackend.domain.admin.domain.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class AdminTokenResponse {

    private final String accessToken;
    private final String refreshToken;
    private final LocalDateTime accessExp;
    private final LocalDateTime refreshExp;
    private final Role role;
}
