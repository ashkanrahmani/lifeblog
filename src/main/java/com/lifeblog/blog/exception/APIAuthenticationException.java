package com.lifeblog.blog.exception;

import org.springframework.http.HttpStatus;

public class APIAuthenticationException extends RuntimeException {
    private HttpStatus httpStatus;
    private String message;

    public APIAuthenticationException(HttpStatus httpStatus, String message) {
        this.httpStatus = httpStatus;
        this.message = message;
    }

    public APIAuthenticationException(String messageToSuper, HttpStatus httpStatus, String message) {
        super(messageToSuper);
        this.httpStatus = httpStatus;
        this.message = message;
    }

}
