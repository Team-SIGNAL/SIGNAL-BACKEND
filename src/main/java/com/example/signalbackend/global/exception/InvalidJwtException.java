package com.example.signalbackend.global.exception;

import com.example.signalbackend.global.error.ErrorCode;
import com.example.signalbackend.global.error.GlobalException;

public class InvalidJwtException extends GlobalException {
    public static final GlobalException EXCEPTION = new InvalidJwtException();

    private InvalidJwtException() {
        super(ErrorCode.INVALID_JWT);
    }

}
