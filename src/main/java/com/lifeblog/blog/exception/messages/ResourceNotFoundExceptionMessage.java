package com.lifeblog.blog.exception.messages;

import com.lifeblog.blog.exception.ExceptionGroupCode;

public enum ResourceNotFoundExceptionMessage {

    RESOURCE_NOT_FOUND("100", " %s not found with %s : %s");

    private final String errorMessage;
    private final String errorCode;

    ResourceNotFoundExceptionMessage(String errorCode, String errorMessage) {
        this.errorMessage = errorMessage;
        this.errorCode = errorCode;
    }

    public String getErrorMessage() {
        return ExceptionGroupCode.RESOURCE.getGroupCode() + errorCode + ": " + errorMessage;
    }
}
