package com.personal.kakaopj.basic;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED) // 접근 제한 -> 무분별한 생성 방지
public class ApiResponse<T> {

    private int statusCode;

    private T data;
    private String message;

    public static <T> ApiResponse<T> createSuccess(T data, int code) {
        return new ApiResponse<>(code, data, "successful");
    }

    public static <T> ApiResponse<T> createSuccessNoData(int code) {
        return new ApiResponse<>(code, "successful");
    }

    public static <T> ApiResponse<T> createSuccessWithMsg(T data, String msg, int code) {
        return new ApiResponse<>(code, data, msg);
    }

    public static ApiResponse<?> createSuccessCodeOnly(int code) {
        return new ApiResponse<>(code);
    }

    // Hibernate Validator에 의해 유효하지 않은 데이터로 인해 API 호출이 거부될때 반환
//    public static ApiResponse<?> createFail(BindingResult bindingResult) {
//        Map<String, String> errors = new HashMap<>();
//
//        List<ObjectError> allErrors = bindingResult.getAllErrors();
//        for (ObjectError error : allErrors) {
//            if (error instanceof FieldError) {
//                errors.put(((FieldError) error).getField(), error.getDefaultMessage());
//            } else {
//                errors.put( error.getObjectName(), error.getDefaultMessage());
//            }
//        }
//        return new ApiResponse<>(FAIL_STATUS, errors, null);
//    }

    // 예외 발생으로 API 호출 실패시 반환
    public static ApiResponse<?> createError(String message, int code) {
        return new ApiResponse<>(code, null, message);
    }

    private ApiResponse(int code, T data, String message) {
        this.statusCode = code;
        this.data = data;
        this.message = message;
    }

    private ApiResponse(int code, String message) {
        this.statusCode = code;
        this.message = message;
    }

    private ApiResponse(int code) {
        this.statusCode = code;
    }
}
