package com.example.signalbackend.global.exception;

import com.example.signalbackend.global.error.ErrorCode;
import com.example.signalbackend.global.error.GlobalException;

public class ExpiredJwtException extends GlobalException {
    public static final GlobalException EXCEPTION = new ExpiredJwtException();

    private ExpiredJwtException() {
        super(ErrorCode.EXPIRED_JWT);
    }
}
