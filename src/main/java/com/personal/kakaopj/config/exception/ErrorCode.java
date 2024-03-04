package com.personal.kakaopj.config.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.*;

@Getter
public enum ErrorCode { // BaseResponseStatus와 같은 역할

    SUCCESS(200, "successful", OK),

    // Request 오류
    SEND_MESSAGE_ERROR(502, "bad gate way", BAD_GATEWAY),
    No_USER_EXIST(404, "no user exists", NOT_FOUND),
    NO_MULTI_PROFILE_EXIST(404, "no multi profile exists", NOT_FOUND),
    NO_BACKGROUND_PROFILE_IMG_EXIST(404, "no background image exists", NOT_FOUND),
    NO_PROFILE_EXIST(404, "no profile exists", NOT_FOUND),
    NO_GROUP_CHAT_ROOM_EXIST(404, "no group chat room exists", NOT_FOUND),
    GROUP_CHAT_ROOM_ALREADY_EXIST(404, "group chat room already exists", NOT_FOUND),
    NO_CHAT_ROOM_EXIST(404, "no chat room exists", NOT_FOUND),
    CHAT_ROOM_ALREADY_EXIST(404, "chat room already exists", NOT_FOUND),
    TOO_MANY_ARGUMENTS(429, "no more than 4 multi profiles allowed", TOO_MANY_REQUESTS),
    INVALID_ARGUMENTS(400, "invalid arguments" ,BAD_REQUEST),
    SERVER_ERROR(500, "server error", INTERNAL_SERVER_ERROR);
    /*
    Enum은 본질적으로 불변의 성격을 가지기 때문에 모든 멤버 변수는 final로 정의해서 사용해야 함.
    왜냐하면 Enum타입은 인스턴스를 생성할 수 없고, 모든 열거 인자들이 public static final로 정의 되기 때문에
    thread간 공유가 가능하여 멤버 변수를 final로 설정하지 않으면 의도하지 않게 변경된 값을 사용할 가능성이 존재하기 때문.
     */
    final int code;
    final String message;
    final HttpStatus httpStatus;

    ErrorCode(int code, String message, HttpStatus httpStatus) {
        this.code = code;
        this.message = message;
        this.httpStatus = httpStatus;
    }
}