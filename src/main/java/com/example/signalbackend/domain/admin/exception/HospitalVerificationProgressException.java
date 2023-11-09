package com.example.signalbackend.domain.admin.exception;

import com.example.signalbackend.global.error.exception.ErrorCode;
import com.example.signalbackend.global.error.exception.GlobalException;

public class HospitalVerificationProgressException extends GlobalException {
    public static final GlobalException EXCEPTION = new HospitalVerificationProgressException();

    private HospitalVerificationProgressException() {
        super(ErrorCode.ADMIN_VERIFICATION_PROGRESS);
    }
}
