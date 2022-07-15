package com.lifeblog.blog.exception.messages;

import com.lifeblog.blog.exception.ExceptionGroupCode;

public enum APIAuthenticationExceptionErrorMessage {

    INVALID_JWT_TOKEN("100", "Invalid JWT token"),
    INVALID_JWT_SIGNATURE("101", "Invalid JWT signature"),
    EXPIRED_JWT_TOKEN("102", "Expired JWT token"),
    UNSUPPORTED_JWT_TOKEN("103", "Unsupported JWT token"),
    CLAIMS_IS_EMPTY("104", "JWT claims string is empty");

    private final String errorMessage;
    private final String errorCode;

    APIAuthenticationExceptionErrorMessage(String errorCode, String errorMessage) {
        this.errorMessage = errorMessage;
        this.errorCode = errorCode;
    }

    public String getErrorMessage() {
        return ExceptionGroupCode.AUTHENTICATION.getGroupCode() + errorCode + ": " + errorMessage;
    }
}
