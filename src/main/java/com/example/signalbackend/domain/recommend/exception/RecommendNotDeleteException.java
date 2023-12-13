package com.example.signalbackend.domain.recommend.exception;

import com.example.signalbackend.global.error.exception.ErrorCode;
import com.example.signalbackend.global.error.exception.GlobalException;

public class RecommendNotDeleteException extends GlobalException {

    public static final GlobalException EXCEPTION = new RecommendNotDeleteException();

    private RecommendNotDeleteException() {
        super(ErrorCode.RECOMMEND_NOT_DELETE);
    }
}
