package com.example.signalbackend.domain.admin.presentation.dto.request;

import com.example.signalbackend.domain.admin.domain.AuthStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@NoArgsConstructor
@Getter
public class UpdateHospitalAuthStatusRequest {
    @NotNull
    private AuthStatus authStatus;
}
