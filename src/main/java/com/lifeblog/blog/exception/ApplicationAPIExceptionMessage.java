package com.lifeblog.blog.exception;

public enum ApplicationAPIExceptionMessage {

    INVALID_JWT_TOKEN("100", "Invalid JWT token"),
    INVALID_JWT_SIGNATURE("100", "Invalid JWT signature"),
    EXPIRED_JWT_TOKEN("101", "Expired JWT token"),
    UNSUPPORTED_JWT_TOKEN("102", "Unsupported JWT token"),
    CLAIMS_IS_EMPTY("103", "JWT claims string is empty");

    private final String errorMessage;
    private final String errorCode;

    ApplicationAPIExceptionMessage(String errorCode, String errorMessage) {
        this.errorMessage = errorMessage;
        this.errorCode = errorCode;
    }

    public String getErrorMessage() {
        return ExceptionGroupCode.AUTHENTICATION.getGroupCode() + ": " + errorCode + ": " + errorMessage;
    }
}
