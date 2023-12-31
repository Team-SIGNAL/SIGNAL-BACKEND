package com.example.signalbackend.global.utils.token.dto;

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
