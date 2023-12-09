package com.example.signalbackend.domain.admin.presentation.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.UUID;

@Getter
@AllArgsConstructor
public class HospitalAuthDetailsResponse {
    private final UUID id;
    private final String name;
    private final String phone;
    private final String address;
    private final String hospitalImage;
    private final String accountId;
}
