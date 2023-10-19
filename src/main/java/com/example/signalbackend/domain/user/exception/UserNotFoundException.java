package com.example.signalbackend.domain.user.exception;

import com.example.signalbackend.global.error.ErrorCode;
import com.example.signalbackend.global.error.GlobalException;

public class UserNotFoundException extends GlobalException {
    public static final GlobalException EXCEPTION =
            new UserNotFoundException();

    private UserNotFoundException() {
        super(ErrorCode.USER_NOT_FOUND);
    }
}