package com.lifeblog.blog.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public enum ExceptionGroupCode {
    RESOURCE("RSC"),
    BUSINESS("BSS"),
    AUTHENTICATION("AUT");

    private String groupCode;

}
