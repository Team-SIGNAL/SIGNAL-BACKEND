package com.example.signalbackend.domain.admin.exception;

import com.example.signalbackend.global.error.exception.ErrorCode;
import com.example.signalbackend.global.error.exception.GlobalException;

public class AdminNotFoundException extends GlobalException {
    public static final GlobalException EXCEPTION = new AdminNotFoundException();

    private AdminNotFoundException() {
        super(ErrorCode.ADMIN_NOT_FOUND);
    }
}
