package com.example.signalbackend.domain.feed.exception;

import com.example.signalbackend.global.error.exception.ErrorCode;
import com.example.signalbackend.global.error.exception.GlobalException;

public class CreateFeedNotPermissionException extends GlobalException {
    public static final GlobalException EXCEPTION = new CreateFeedNotPermissionException();

    private CreateFeedNotPermissionException() {
        super(ErrorCode.CREATE_FEED_NOT_PERMISSION);
    }
}
