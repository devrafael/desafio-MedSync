package com.conecte.medsync.dtos.requests;

import com.conecte.medsync.models.user.UserRole;

public record RegisterRequest(
        String email,
        String password,
        String fullName,
        UserRole role) {
}
