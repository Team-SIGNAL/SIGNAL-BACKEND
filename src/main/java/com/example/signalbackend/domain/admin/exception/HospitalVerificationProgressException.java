package com.example.signalbackend.domain.admin.exception;

import com.example.signalbackend.global.error.exception.ErrorCode;
import com.example.signalbackend.global.error.exception.GlobalException;

public class AdminVerificationProgressException extends GlobalException {
    public static final GlobalException EXCEPTION = new AdminVerificationProgressException();

    private AdminVerificationProgressException() {
        super(ErrorCode.ADMIN_VERIFICATION_PROGRESS);
    }
}
