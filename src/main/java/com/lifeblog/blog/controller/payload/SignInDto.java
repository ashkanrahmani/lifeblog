package com.lifeblog.blog.controller.payload;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class SignInDto {
    private String username;
    private String password;

    @JsonIgnore
    private boolean isSignedIn = false;
}
