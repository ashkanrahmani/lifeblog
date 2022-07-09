package com.lifeblog.blog.service.impl;

import com.lifeblog.blog.controller.payload.SignInDto;
import com.lifeblog.blog.controller.payload.SignUpDto;
import com.lifeblog.blog.entity.Role;
import com.lifeblog.blog.entity.User;
import com.lifeblog.blog.repository.RoleRepository;
import com.lifeblog.blog.repository.UserRepository;
import com.lifeblog.blog.service.AuthenticationService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;


    public AuthenticationServiceImpl(AuthenticationManager authenticationManager, UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }


    @Override
    public SignInDto signInUser(SignInDto signInDto) {
        Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(signInDto.getUsername(), signInDto.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authenticate);
        signInDto.setSignedIn(true);
        return signInDto;
    }

    @Override
    public SignUpDto signUnUser(SignUpDto signUpDto) {
        if (userRepository.existsByUsername(signUpDto.getUsername()) || userRepository.existsByEmail(signUpDto.getEmail())) {
            signUpDto.setSignedUp(false);
            return signUpDto;
        }

        User user = new User();
        user.setEmail(signUpDto.getEmail());
        user.setUsername(signUpDto.getUsername());
        user.setName(signUpDto.getName());
        user.setPassword(passwordEncoder.encode(signUpDto.getPassword()));

        Role role = roleRepository.findByName("ROLE_ADMIN").get();
        user.setRoles(Collections.singleton(role));
        userRepository.save(user);
        signUpDto.setSignedUp(true);
        return signUpDto;
    }
}
