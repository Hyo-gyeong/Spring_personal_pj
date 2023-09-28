package com.personal.kakaopj.config.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.util.Map;

@Getter
@Setter
@AllArgsConstructor
public class BaseException extends RuntimeException { // RuntimeException이 발생하면 여기로 와서 생성자 만듦
    ErrorCode errorCode;
    String message;
    HttpStatus httpStatus;
    Map<String, String> data;

    public BaseException(ErrorCode errorCode) {
        super();
        this.errorCode = errorCode;
        this.message = errorCode.getMessage();
        this.httpStatus = errorCode.getHttpStatus();
    }
}