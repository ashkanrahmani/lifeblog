package com.lifeblog.blog.exception;

import org.springframework.http.HttpStatus;

import java.io.Serial;

public class BusinessException extends RuntimeException{
    @Serial
    private static final long serialVersionUID = 3642105980276596378L;

    private HttpStatus httpStatus;
    private String message;

    public BusinessException(HttpStatus httpStatus, String message) {
        this.httpStatus = httpStatus;
        this.message = message;
    }

    public BusinessException(String msgToSuperClass, HttpStatus httpStatus, String message) {
        super(msgToSuperClass);
        this.httpStatus = httpStatus;
        this.message = message;
    }

    public BusinessException(String msgToSuperClass, Throwable cause, HttpStatus httpStatus, String message) {
        super(msgToSuperClass, cause);
        this.httpStatus = httpStatus;
        this.message = message;
    }
}
