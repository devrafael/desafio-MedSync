package com.conecte.medsync.controllers;

import com.conecte.medsync.dtos.responses.UserResponse;
import com.conecte.medsync.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/users")
@RestController
public class UserContoller {

    private final UserService userService;
    public UserContoller(UserService userService) {
        this.userService = userService;
    }


    @PreAuthorize("hasRole('PATIENT')")
    @GetMapping
    public ResponseEntity<List<UserResponse>> getAllDoctors() {
        List<UserResponse> doctors = userService.getAllDoctors();
        return ResponseEntity.ok().body(doctors);
    }




}
