package com.example.signalbackend.domain.recommend.exception;

import com.example.signalbackend.global.error.exception.ErrorCode;
import com.example.signalbackend.global.error.exception.GlobalException;

public class RecommendNotFoundException extends GlobalException {

    public static final GlobalException EXCEPTION = new RecommendNotFoundException();

    private RecommendNotFoundException() {
        super(ErrorCode.RECOMMEND_NOT_FOUND);
    }
}
