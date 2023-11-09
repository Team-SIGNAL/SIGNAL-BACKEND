package com.example.signalbackend.global.utils.s3.exception;

import com.example.signalbackend.global.error.exception.ErrorCode;
import com.example.signalbackend.global.error.exception.GlobalException;

public class FileIsEmptyException extends GlobalException {
    public static final GlobalException EXCEPTION = new FileIsEmptyException();

    private FileIsEmptyException() {
        super(ErrorCode.FILE_IS_EMPTY);
    }
}
