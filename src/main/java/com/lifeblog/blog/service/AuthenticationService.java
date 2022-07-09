package com.lifeblog.blog.service;

import com.lifeblog.blog.controller.payload.SignInDto;
import com.lifeblog.blog.controller.payload.SignUpDto;

public interface AuthenticationService {
    SignInDto signInUser(SignInDto signInDto);
    SignUpDto signUnUser(SignUpDto signUpDto);
}
