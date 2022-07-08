package com.lifeblog.blog.exception;

import org.springframework.http.HttpStatus;

public class ApplicationAPIException extends RuntimeException {
    private HttpStatus httpStatus;
    private String message;

    public ApplicationAPIException(HttpStatus httpStatus, String message) {
        this.httpStatus = httpStatus;
        this.message = message;
    }

    public ApplicationAPIException(String messageToSuper, HttpStatus httpStatus, String message) {
        super(messageToSuper);
        this.httpStatus = httpStatus;
        this.message = message;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public void setHttpStatus(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
