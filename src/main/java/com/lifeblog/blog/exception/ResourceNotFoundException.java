package com.lifeblog.blog.exception;

import java.io.Serial;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 6639767937317695877L;

    private final String resourceName;
    private final String fieldName;
    private final String fieldValue;
    private final String message;

    public ResourceNotFoundException(String message, String resourceName, String fieldName, String fieldValue) {
        super(String.format(message, resourceName, fieldName, fieldValue));
        this.resourceName = resourceName;
        this.fieldName = fieldName;
        this.fieldValue = fieldValue;
        this.message = message;
    }

    public ResourceNotFoundException(String message, String resourceName, String fieldName, String fieldValue, Throwable cause) {
        super(String.format(message, resourceName, fieldName, fieldValue), cause);
        this.resourceName = resourceName;
        this.fieldName = fieldName;
        this.fieldValue = fieldValue;
        this.message = message;
    }
}
