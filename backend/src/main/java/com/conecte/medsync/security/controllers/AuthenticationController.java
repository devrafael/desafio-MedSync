package com.conecte.medsync.security.controllers;

import com.conecte.medsync.dtos.requests.AuthenticationRequest;
import com.conecte.medsync.dtos.requests.RegisterRequest;
import com.conecte.medsync.dtos.responses.LoginResponse;
import com.conecte.medsync.models.user.UserModel;
import com.conecte.medsync.security.services.AuthenticationService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("auth")
public class AuthenticationController {



    private final AuthenticationService authenticationService;

    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> Login(
            @RequestBody @Valid AuthenticationRequest authenticationRequest){
        LoginResponse loginResponse = authenticationService.Login(authenticationRequest);
        return ResponseEntity.ok().body(loginResponse);

    }

    @PostMapping("/register")
    public ResponseEntity<Void> Register(@RequestBody @Valid RegisterRequest registerRequest){
        UserModel user = authenticationService.Register(registerRequest);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(user.getUserId())
                .toUri();
        return ResponseEntity.created(uri).build();
    }



}
