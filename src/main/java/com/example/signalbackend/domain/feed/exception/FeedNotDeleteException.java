package com.example.signalbackend.domain.feed.exception;

import com.example.signalbackend.global.error.exception.ErrorCode;
import com.example.signalbackend.global.error.exception.GlobalException;

public class FeedNotDeleteException extends GlobalException {
    public static final GlobalException EXCEPTION = new FeedNotDeleteException();

    private FeedNotDeleteException() {
        super(ErrorCode.FEED_NOT_DELETE);
    }
}
