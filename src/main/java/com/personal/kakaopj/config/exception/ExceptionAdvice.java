package com.personal.kakaopj.config.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ValidationException;

@Slf4j
@RestControllerAdvice
public class ExceptionAdvice {
    /**
     * Custom Exception 핸들링
     */
    @ExceptionHandler(BaseException.class)
    public ResponseEntity onBaseException(BaseException exception){
        return new ResponseEntity<>(BaseResponse.onFailure(exception.getErrorCode().getCode(), exception.getMessage(), exception.getData()), null, exception.getHttpStatus());
    }
}