package com.lifeblog.blog.exception.messages;

import com.lifeblog.blog.exception.ExceptionGroupCode;

public enum BusinessExceptionErrorMessage {
    POST_TITLE_IS_NOT_UNIQUE("100", "Post title must be unique");

    private final String errorMessage;
    private final String errorCode;

    BusinessExceptionErrorMessage(String errorCode, String errorMessage) {
        this.errorMessage = errorMessage;
        this.errorCode = errorCode;
    }

    public String getErrorMessage() {
        return ExceptionGroupCode.BUSINESS.getGroupCode() + errorCode + ": " + errorMessage;
    }
}
