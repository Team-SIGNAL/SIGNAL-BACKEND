package com.example.signalbackend.domain.admin.presentation.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class AdminInfoResponse {
    private final String name;
    private final String phone;
    private final String profile;
    private final Boolean isNotAuthRequest;
}
