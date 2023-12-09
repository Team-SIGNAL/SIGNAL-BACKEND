package com.example.signalbackend.domain.admin.presentation.dto.response;

import lombok.Builder;
import lombok.Getter;

import java.util.UUID;

@Getter
@Builder
public class HospitalAuthRequestElement {
    private final UUID id;
    private final String name;
    private final String phone;
}
