package com.lifeblog.blog.controller;

import com.lifeblog.blog.controller.payload.JwtAuthResponse;
import com.lifeblog.blog.controller.payload.SignInDto;
import com.lifeblog.blog.controller.payload.SignUpDto;
import com.lifeblog.blog.service.AuthenticationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(value = "AuthController provide signup and signin api")
@RestController
@RequestMapping("/api/auth")
public class AuthenticationController {

    private final AuthenticationService authenticationService;
    Logger logger = LoggerFactory.getLogger(AuthenticationController.class);

    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @ApiOperation(value = "Signin API")
    @PostMapping("/signin")
    public ResponseEntity<JwtAuthResponse> authenticateUser(@RequestBody SignInDto signinDto) {
        authenticationService.signInUser(signinDto);
        if (signinDto.isSignedIn()) {
            logger.info("User signed in");
            return ResponseEntity.ok(new JwtAuthResponse(signinDto.getToken()));
        }
        logger.info("User can not signed in");
        return ResponseEntity.ok(new JwtAuthResponse(""));
    }

    @ApiOperation(value = "Signup API")
    @PostMapping("/signup")
    public ResponseEntity<String> signUpUser(@RequestBody SignUpDto signUpDto) {
        authenticationService.signUnUser(signUpDto);
        if (!signUpDto.isSignedUp()) {
            logger.info("Username is already taken, Signup failed");
            return new ResponseEntity<>("Username is already taken", HttpStatus.BAD_REQUEST);
        }
        logger.info("signed up successfully");
        return new ResponseEntity<>("User successfully registered", HttpStatus.OK);
    }
}
