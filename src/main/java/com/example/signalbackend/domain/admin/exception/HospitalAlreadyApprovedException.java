package com.example.signalbackend.domain.admin.exception;

import com.example.signalbackend.global.error.exception.ErrorCode;
import com.example.signalbackend.global.error.exception.GlobalException;

public class HospitalAlreadyApprovedException extends GlobalException {
    public static final GlobalException EXCEPTION = new HospitalAlreadyApprovedException();

    private HospitalAlreadyApprovedException() {
        super(ErrorCode.ADMIN_ALREADY_APPROVED);
    }
}
