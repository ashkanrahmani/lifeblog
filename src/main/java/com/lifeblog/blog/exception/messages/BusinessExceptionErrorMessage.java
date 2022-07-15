package com.lifeblog.blog.exception.messages;

import com.lifeblog.blog.exception.ExceptionGroupCode;

public enum BusinessExceptionErrorMessage {
    POST_TITLE_IS_NOT_UNIQUE("100", "Post title must be unique"),
    COMMENT_NOT_BELONG_TO_POST("100", "This comments is not belong to post");


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
