package com.example.signalbackend.domain.user.exception;

import com.example.signalbackend.global.error.ErrorCode;
import com.example.signalbackend.global.error.GlobalException;

public class PasswordMismatchException extends GlobalException {
    public static final GlobalException EXCEPTION = new PasswordMismatchException();

    private PasswordMismatchException() {
        super(ErrorCode.PASSWORD_MISMATCH);
    }
}
