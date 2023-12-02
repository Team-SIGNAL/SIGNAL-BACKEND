package com.example.signalbackend.domain.admin.presentation.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class HospitalAuthRequestListResponse {
    private final List<HospitalAuthRequestElement> authRequestList;
}
