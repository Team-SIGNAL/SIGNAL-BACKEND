package com.example.signalbackend.global.exception;

import com.example.signalbackend.global.error.exception.ErrorCode;
import com.example.signalbackend.global.error.exception.GlobalException;

public class PasswordMixmatchException extends GlobalException {
    public static final GlobalException EXCEPTION = new PasswordMixmatchException();

    private PasswordMixmatchException() {
        super(ErrorCode.PASSWORD_MISMATCH);
    }
}
