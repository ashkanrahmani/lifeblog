package com.lifeblog.blog.exception;

import org.springframework.http.HttpStatus;

import java.io.Serial;

public class APIAuthenticationException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 1866551679932308875L;

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
