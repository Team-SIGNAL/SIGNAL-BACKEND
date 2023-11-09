package com.example.signalbackend.global.utils.s3.exception;

import com.example.signalbackend.global.error.exception.ErrorCode;
import com.example.signalbackend.global.error.exception.GlobalException;

public class FileSaveFailedException extends GlobalException {
    public static final GlobalException EXCEPTION = new FileSaveFailedException();

    private FileSaveFailedException() {
        super(ErrorCode.FILE_SAVE_FAILED);
    }
}
