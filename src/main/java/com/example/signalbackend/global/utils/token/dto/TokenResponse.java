package com.example.signalbackend.global.utils.token.dto;

import com.example.signalbackend.domain.admin.domain.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
public class TokenResponse {
    private final String accessToken;
    private final String refreshToken;
    private final LocalDateTime accessExpiredAt;
    private final LocalDateTime refreshExpiredAt;
}
