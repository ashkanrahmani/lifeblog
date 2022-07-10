package com.lifeblog.blog.controller.payload;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
public class JwtAuthResponse {
    private final String tokenType = "Bearer";
    private String accessToken;

    public JwtAuthResponse(String accessToken) {
        this.accessToken = accessToken;
    }
}
