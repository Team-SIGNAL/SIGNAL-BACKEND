package com.example.signalbackend.domain.diary.exception;

import com.example.signalbackend.global.error.exception.ErrorCode;
import com.example.signalbackend.global.error.exception.GlobalException;

public class DiaryNotFoundException extends GlobalException {
    public static final GlobalException EXCEPTION = new DiaryNotFoundException();

    private DiaryNotFoundException() {
        super(ErrorCode.DIARY_NOT_FOUND);
    }
}
