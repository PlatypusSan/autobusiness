package com.test.autobusiness.controllers;

import com.test.autobusiness.entities.dto.AuthenticationRequest;
import com.test.autobusiness.services.impl.UserServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping(value = "/api/v1/auth")
public class AuthenticationController {

    private final UserServiceImpl userService;

    public AuthenticationController(UserServiceImpl userService) {

        this.userService = userService;
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> login(@RequestBody AuthenticationRequest authenticationRequest) {

        return userService.login(authenticationRequest);
    }
}
