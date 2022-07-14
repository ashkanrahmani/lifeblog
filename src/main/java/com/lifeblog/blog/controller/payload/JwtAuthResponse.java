package com.lifeblog.blog.controller.payload;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@ApiModel(value = "JWT Model")
@Setter
@Getter
public class JwtAuthResponse {
    private final String tokenType = "Bearer";
    @ApiModelProperty(value = "JWT Token")
    private String accessToken;

    public JwtAuthResponse(String accessToken) {
        this.accessToken = accessToken;
    }
}
