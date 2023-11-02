package com.personal.kakaopj.config.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.*;

@Getter
public enum ErrorCode { // BaseResponseStatus와 같은 역할

    SUCCESS(200, "successful", OK),

    // Request 오류
    No_USER_EXIST(404, "no user exists", NOT_FOUND),
    NO_MULTI_PROFILE_EXIST(404, "no multi profile exists", NOT_FOUND),
    NO_BACKGROUND_PROFILE_IMG_EXIST(404, "no background image exists", NOT_FOUND),
    NO_PROFILE_EXIST(404, "no profile exists", NOT_FOUND),
    NO_GROUP_CHAT_ROOM_EXIST(404, "no group chat room exists", NOT_FOUND),
    TOO_MANY_ARGUMENTS(429, "no more than 4 multi profiles allowed", TOO_MANY_REQUESTS),
    INVALID_ARGUMENTS(400, "invalid arguments" ,BAD_REQUEST),
    SERVER_ERROR(500, "server error", INTERNAL_SERVER_ERROR);

    private int code;
    private String message;
    private HttpStatus httpStatus;

    ErrorCode(int code, String message, HttpStatus httpStatus) {
        this.code = code;
        this.message = message;
        this.httpStatus = httpStatus;
    }
}