package com.example.signalbackend.global.exception;

import com.example.signalbackend.global.error.ErrorCode;
import com.example.signalbackend.global.error.GlobalException;

public class InternerServerException extends GlobalException {

    public static final GlobalException EXCEPTION = new InternerServerException();

    private InternerServerException() {
        super(ErrorCode.INTERNAL_SERVER_ERROR);
    }
}
