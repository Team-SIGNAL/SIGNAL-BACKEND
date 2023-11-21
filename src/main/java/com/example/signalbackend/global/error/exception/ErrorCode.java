package com.example.signalbackend.global.error.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum ErrorCode {

    ADMIN_NOT_FOUND(404, "ADMIN-404-1", "Admin Not Found."),
    ADMIN_ALREADY(409, "ADMIN-409-1", "Admin Already"),
    ADMIN_ALREADY_APPROVED(409, "ADMIN-409-2", "Admin Already Approved"),
    ADMIN_VERIFICATION_PROGRESS(409, "ADMIN-409-3", "Admin Verification Progress"),

    USER_ALREADY(409, "USER-409-1", "User Already"),
    USER_NOT_FOUND(404, "USER-404-1", "User Not Found"),
    PASSWORD_MISMATCH(401, "USER-401-1", "Password Mismatch"),

    FILE_IS_EMPTY(400, "FILE-400-1", "File Is Empty"),
    FILE_SAVE_FAILED(400, "FILE-400-2", "File Save Failed"),

    CREATE_FEED_NOT_PERMISSION(409, "FEED-409-1", "Create Feed Not Permission"),
    FEED_NOT_FOUND(404, "FEED-400-1", "Feed Not Found"),
    FEED_NOT_DELETE(401, "FEED-401-1", "Feed Not Delete"),
    FEED_NOT_UPDATE(401, "FEED-401-2", "Feed Not Update"),

    DIARY_NOT_FOUND(404, "DIARY-404-1", "Diary Not Found"),
    DIARY_NOT_DELETE(401, "DIARY-401-1", "Diary Not Delete"),

    RESERVATION_NOT_FOUND(404, "RESERVATION-400-1", "Reservation Not Found"),

    RECOMMEND_NOT_FOUND(404, "RECOMMEND-404-1", "Recommend Not Found"),
    RECOMMEND_NOT_DELETE(401, "RECOMMEND-401-1", "Recommend Not Delete"),

    EXPIRED_TOKEN(401, "COMMON-401-2", "Expired Token"),
    INVALID_TOKEN(401, "COMMON-401-1", "Invalid Token"),
    INTERNAL_SERVER_ERROR(500, "COMMON-500-1", "Internal Server Error");

    private final int status;
    private final String code;
    private final String message;
}
