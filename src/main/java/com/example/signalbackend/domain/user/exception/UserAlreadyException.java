package com.example.signalbackend.domain.user.exception;

import com.example.signalbackend.global.error.exception.ErrorCode;
import com.example.signalbackend.global.error.exception.GlobalException;

public class UserAlreadyException extends GlobalException {
    public static final GlobalException EXCEPTION = new UserAlreadyException();

    private UserAlreadyException() {
        super(ErrorCode.USER_ALREADY);
    }
}
