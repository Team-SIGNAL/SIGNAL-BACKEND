package com.example.signalbackend.domain.admin.presentation.dto.response;

import com.example.signalbackend.domain.admin.domain.AuthStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class HospitalInfoResponse {
    private final String name;
    private final String phone;
    private final String profile;
    private final AuthStatus authStatus;
    private final String address;
    private final String hospitalImage;
}
