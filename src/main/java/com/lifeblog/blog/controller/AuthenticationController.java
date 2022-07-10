package com.lifeblog.blog.controller;

import com.lifeblog.blog.controller.payload.JwtAuthResponse;
import com.lifeblog.blog.controller.payload.SignInDto;
import com.lifeblog.blog.controller.payload.SignUpDto;
import com.lifeblog.blog.service.AuthenticationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping("/signin")
    public ResponseEntity<JwtAuthResponse> authenticateUser(@RequestBody SignInDto signinDto) {
        authenticationService.signInUser(signinDto);
        if (signinDto.isSignedIn()) {
            return  ResponseEntity.ok(new JwtAuthResponse(signinDto.getToken()));
        }
        return  ResponseEntity.ok(new JwtAuthResponse(""));
    }

    @PostMapping("/signup")
    public ResponseEntity<String> signUpUser(@RequestBody SignUpDto signUpDto) {
        authenticationService.signUnUser(signUpDto);
        if (!signUpDto.isSignedUp()) {
            return new ResponseEntity<>("Username is already taken", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>("User successfully registered", HttpStatus.OK);
    }
}
