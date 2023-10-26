package com.example.signalbackend.global.error;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum ErrorCode {
    INTERNAL_SERVER_ERROR(500, "SERVER-500-1", "Internel Server Error");

    private final int status;
    private final String code;
    private final String message;
}
