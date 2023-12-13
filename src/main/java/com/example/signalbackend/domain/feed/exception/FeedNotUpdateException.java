package com.example.signalbackend.domain.feed.exception;

import com.example.signalbackend.global.error.exception.ErrorCode;
import com.example.signalbackend.global.error.exception.GlobalException;

public class FeedNotUpdateException extends GlobalException {

    public static final GlobalException EXCEPTION = new FeedNotUpdateException();

    private FeedNotUpdateException() {
        super(ErrorCode.FEED_NOT_UPDATE);
    }
}
