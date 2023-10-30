package com.example.signalbackend.domain.admin.exception;

import com.example.signalbackend.global.error.exception.ErrorCode;
import com.example.signalbackend.global.error.exception.GlobalException;

public class AdminAlreadyException extends GlobalException {
    public static final GlobalException EXCEPTION = new AdminAlreadyException();

    private AdminAlreadyException() {
        super(ErrorCode.ADMIN_ALREADY);
    }
}
