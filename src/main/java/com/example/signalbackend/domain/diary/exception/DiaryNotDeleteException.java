package com.example.signalbackend.domain.diary.exception;

import com.example.signalbackend.global.error.exception.ErrorCode;
import com.example.signalbackend.global.error.exception.GlobalException;

public class DiaryNotDeleteException extends GlobalException {
    public static final GlobalException EXCEPTION = new DiaryNotDeleteException();

    private DiaryNotDeleteException() {
        super(ErrorCode.DIARY_NOT_DELETE);
    }
}
