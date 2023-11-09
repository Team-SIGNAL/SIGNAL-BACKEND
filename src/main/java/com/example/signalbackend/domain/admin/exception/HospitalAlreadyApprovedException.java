package com.example.signalbackend.domain.admin.exception;

import com.example.signalbackend.global.error.exception.ErrorCode;
import com.example.signalbackend.global.error.exception.GlobalException;

public class AdminAlreadyApprovedException extends GlobalException {
    public static final GlobalException EXCEPTION = new AdminAlreadyApprovedException();

    private AdminAlreadyApprovedException() {
        super(ErrorCode.ADMIN_ALREADY_APPROVED);
    }
}
